pipeline {
    agent { label 'test_con' }

    environment {
        SLACK_CHANNEL = '#jenkins-status'  // Set your Slack channel
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
            // Always notify Slack about pipeline completion
            slackSend(channel: SLACK_CHANNEL, message: "Pipeline execution completed!", credentialsId: SLACK_CREDENTIALS_ID)
            echo 'Pipeline execution completed!'
            jacoco()
        }
        success {
            // Notify Slack on success
            slackSend(channel: SLACK_CHANNEL, message: "Pipeline succeeded! :tada:", credentialsId: SLACK_CREDENTIALS_ID)
            echo 'Pipeline succeeded! :tada:'
        }
        failure {
            // Notify Slack on failure
            slackSend(channel: SLACK_CHANNEL, message: "Pipeline failed. Check the logs! :x:", credentialsId: SLACK_CREDENTIALS_ID)
            echo 'Pipeline failed. Check the logs! :x:'
        }
    }
}
