properties([disableConcurrentBuilds()])

pipeline {
    agent any
    triggers { pollSCM('* * * * *')}
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        timestamps()
    }
    stages {
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
                    dir('docker') {
                        sh 'docker push pumpumpam/cicd_test:latest'
                    }
                }
            }
    }
}
