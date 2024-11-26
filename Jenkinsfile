pipeline {
    agent { label 'test_con' }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/abdullaal7/TestB.git', branch: 'main'
            }
        }

        stage('Build Application') {
            steps {
                script {
                    // Assuming you're using Maven to build the Java project
                    bat 'mvn clean package'
                }
            }
        }

        stage('SonarQube Analysis and test coverage') {
            steps {
                script {
                    // Run tests and generate test coverage report with JaCoCo
                    // Execute SonarQube Scanner with JaCoCo test coverage report
                    withSonarQubeEnv('SonarQube') {
                        bat 'mvn clean verify sonar:sonar'
                    }
                }
            }
        }

        stage('Build and Push with Docker Compose') {
            steps {
                script {
                    def imageName = 'abdullaal77/player-app:latest'

                    bat "docker-compose build"
                    bat "docker tag testb-player ${imageName}"
                    bat "docker login -u abdullaal77 -p #\$AaPwD56"
                    bat "docker push ${imageName}"
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    bat "docker-compose pull"
                    bat "docker-compose up -d"
                }
            }
        }
    }

    post {
        always {
            // Publish JaCoCo code coverage report
            jacoco()
            echo 'Pipeline execution completed!'
        }
        success {
            echo 'Application deployed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for details.'
        }
    }
}
