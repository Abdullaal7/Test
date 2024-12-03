pipeline {
    agent { label 'test_con' }

    environment {
        DOCKER_IMAGE = 'abdullaal77/player-app' // Your Docker image name
        DOCKER_TAG = 'latest' // Docker image tag
        SLACK_CHANNEL = '#jenkins-status' // Set your Slack channel
        SLACK_CREDENTIALS_ID = 'slack_token' // Your Slack credentials ID
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials' // Your Docker credentials ID (stored in Jenkins credentials store)
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

                    // Using Docker credentials securely
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat "docker-compose build"
                        bat "docker tag testb-player ${imageName}"
                        bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                        bat "docker push ${imageName}"
                    }
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
			// Always notify Slack about pipeline completion
            slackSend(channel: SLACK_CHANNEL, message: "Pipeline execution completed!", tokenCredentialId: SLACK_CREDENTIALS_ID)
            jacoco()
            echo 'Pipeline execution completed!'
        }
        success {
            // Notify Slack on success
            slackSend(channel: SLACK_CHANNEL, message: "Application deployed successfully! :tada:", tokenCredentialId: SLACK_CREDENTIALS_ID)
            echo 'Application deployed successfully! :tada:'
        }
        failure {
            // Notify Slack on failure
            slackSend(channel: SLACK_CHANNEL, message: "Pipeline failed. Check the logs for details. :x:", tokenCredentialId: SLACK_CREDENTIALS_ID)
            echo 'Pipeline failed. Check the logs for details.'
        }
    }
}
