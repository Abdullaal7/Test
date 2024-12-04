pipeline {
    agent { label 'test_con' }

    environment {
        DOCKER_IMAGE = 'abdullaal77/player-app' // Your Docker image name
        DOCKER_TAG = 'latest' // Docker image tag
        DOCKER_USER = 'abdullaal77' // Docker username
        DOCKER_PASS = '#\$AaPwD56' // Docker password (escaped special characters if needed)
        SLACK_CHANNEL = '#jenkins-status' // Set your Slack channel
        SLACK_CREDENTIALS_ID = 'slack_token' // Your Slack credentials ID
    }

    stages {
        stage('Notify Start') {
            steps {
                script {
                    // Notify Slack that the pipeline has started
                    slackSend(channel: SLACK_CHANNEL, message: "Pipeline has started! :rocket:", tokenCredentialId: SLACK_CREDENTIALS_ID)
                }
            }
        }

        stage('Checkout') {
            steps {
                // Clone the repository using SSH (use the SSH URL and specify credentials)
                git(
                    url: 'git@github.com:abdullaal7/TestB.git', 
                    branch: 'main',
                    credentialsId: 'test_Rebo' // Use the SSH credentials ID you added to Jenkins
                )
            }
        }

        stage('Build Application') {
            steps {
                script {
                    bat 'mvn clean package'
                }
            }
        }

        stage('SonarQube Analysis and test coverage') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        bat 'mvn clean verify sonar:sonar'
                    }
                }
            }
        }

        stage('Build and Push with Docker Compose') {
            steps {
                script {
                    def imageName = "${DOCKER_IMAGE}:${DOCKER_TAG}"
                    bat "docker-compose build"
                    bat "docker tag testb-player ${imageName}"

                    // Login to Docker Hub using environment variables for username and password
                    bat "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"

                    // Push the Docker image to the registry
                    bat "docker push ${imageName}"
                }
            }
        }

        // Add additional deployment or other stages here as needed
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
