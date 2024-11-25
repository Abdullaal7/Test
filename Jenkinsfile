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
                    bat 'mvn clean package'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Execute SonarQube Scanner
                    withSonarQubeEnv('SonarQube') {
                        bat 'mvn sonar:sonar -Dsonar.projectKey=TestB -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<TOKEN>'
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
