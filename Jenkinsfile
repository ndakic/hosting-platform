pipeline {
    environment {
        GIT_COMMIT_INFO = sh(returnStdout: true, script: 'git log | head -3').trim()
        GIT_MESSAGE = sh(returnStdout: true, script: 'git log -1 --pretty=%B').trim()
    }
    agent {
        dockerfile {
            filename 'config/jenkins.dockerfile'
        }
    }
    stages {
        stage('Compile') {
            steps {
                dir("hostplat-server") {
                    sh 'mvn -B -DskipTests clean compile'
                }
            }
        }
        stage('Test') {
            steps {
                dir("hostplat-server") {
                    sh 'mvn -B test'
                }
            }
        }
        stage('Uber JAR') {
            steps {
                dir("hostplat-server") {
                    sh 'mvn -B package'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'chmod +x config/deploy.sh'
                sh 'config/deploy.sh'
            }
        }
    }

    post {
        success {
            slackSend color: "#0be00f", message: "Build Succeed - ${env.GIT_COMMIT_INFO} \nCommit message: ${env.GIT_MESSAGE}"
        }
        failure {
            slackSend color: "#c31e26", message: "Build failed - ${env.GIT_COMMIT_INFO} \nCommit message: ${env.GIT_MESSAGE}"
        }
    }
}