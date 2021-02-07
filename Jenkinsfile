pipeline {
    environment {
        GIT_COMMIT_INFO = sh(returnStdout: true, script: 'git log | head -3').trim()
        GIT_MESSAGE = sh(returnStdout: true, script: 'git log -1 --pretty=%B').trim()
        HUB_USERNAME = credentials('hub-username')
        HUB_PASSWORD = credentials('hub-password')
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
        stage('Build/Push Image') {
            steps {
                dir("hostplat-server") {
                    sh 'echo $HUB_PASSWORD | docker login --username $HUB_USERNAME --password-stdin'
                    sh 'service docker start && gpasswd -a $USER docker'
                    sh 'service docker status'
                    sh 'ls'
                    sh 'sudo docker build -f Dockerfile -t hostplat-server .'
                    sh 'sudo docker tag hostplat-server:latest ndakic/hostplat-server:latest'
                    sh 'sudo docker push ndakic/hostplat-server:latest'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'chmod +x config/deploy.sh'
                sh 'chmod 400 config/hostplat-server.pem'
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