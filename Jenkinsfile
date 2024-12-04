pipeline {
    agent { label 'test_con' } // Specify your Jenkins agent label

    environment {
        DOCKER_IMAGE = 'abdullaal77/player-app' // Docker image name
        DOCKER_TAG = 'latest' // Docker image tag
        DOCKER_USER = 'abdullaal77' // Docker username
        DOCKER_PASS = '#\$AaPwD56' // Docker password (escaped special characters if needed)
        SLACK_CHANNEL = '#jenkins-status' // Slack channel for notifications
        SLACK_CREDENTIALS_ID = 'slack_token' // Slack credentials ID for notifications
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
        // Clone the repository using SSH (SSH URL and SSH credentials)
        git(
            url: 'git@github.com:abdullaal7/TestB.git',
            branch: 'main',
            credentialsId: 'test_Rebo', // The SSH credentials ID for accessing GitHub
            changelog: false,
            poll: false
        )
    }
}

        stage('Build Application') {
            steps {
                script {
                    bat 'mvn clean package' // Use Maven to build the application
                }
            }
        }

        stage('SonarQube Analysis and test coverage') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        bat 'mvn clean verify sonar:sonar' // Run SonarQube analysis
                    }
                }
            }
        }

        stage('Build and Push with Docker Compose') {
            steps {
                script {
                    def imageName = "${DOCKER_IMAGE}:${DOCKER_TAG}"
                    bat "docker-compose build" // Build Docker image using Docker Compose
                    bat "docker tag testb-player ${imageName}" // Tag the built image

                    // Login to Docker Hub using environment variables for username and password
                    bat "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"

                    // Push the Docker image to the registry
                    bat "docker push ${imageName}"
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    // Pull and deploy the container using Docker Compose
                    bat "docker-compose pull"
                    bat "docker-compose up -d"
                }
            }
        }
    }

    post {
        always {
            // Always notify Slack about pipeline completion
            slackSend(channel: SLACK_CHANNEL, message: "Pipeline execution completed!", tokenCredentialId: SLACK_CREDENTIALS_ID)
            // Clean up unused Docker images, containers, networks, etc.
            bat "docker system prune -f"
            echo 'Pipeline execution completed!'
        }
        success {
            slackSend(channel: SLACK_CHANNEL, message: "Pipeline succeeded! :tada:", tokenCredentialId: SLACK_CREDENTIALS_ID)
        }
        failure {
            slackSend(channel: SLACK_CHANNEL, message: "Pipeline failed. Check the logs! :x:", tokenCredentialId: SLACK_CREDENTIALS_ID)
        }
    }
}
