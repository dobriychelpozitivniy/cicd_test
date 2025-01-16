properties([disableConcurrentBuilds()])

pipeline {
    agent any
    triggers { pollSCM('* * * * *')}
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        timestamps()
    }
    stages {
        stage("docker logi ") {
            steps {
                echo "  ================== start docker login =================="
                withCredentials([usernamePassword(credentialsId: 'docker_hub_private_key', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh """
                    docker login -u $USERNAME
                    """
                    sh """
                    $PASSWORD
                    """
                }
            }
        }
        stage("create docker image") {
            steps {
                echo "  ================== start building image =================="
                dir('docker') {
                    sh 'docker build -t pumpumpam/cicd_test:latest .'
                }
            }
        }
        stage("docker PUSH") {
                steps {
                    echo "  ==================  start pushing image =================="
                    sh 'docker push pumpumpam/cicd_test:latest'
                }
            }
    }
}
