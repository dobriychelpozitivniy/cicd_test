#!groovy
// Check ub1 properties
properties([disableConcurrentBuilds()])

pipeline {
    agent any // Использует любой доступный агент
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        timestamps()
    }
    stages {
        stage("First step") {
            steps {
                sh 'ssh root@ub1 \'hostname\''
            }
        }
        stage("Second step") {
            steps {
                sh 'ssh root@ub1 \'uptime\''
            }
        }
    }
}
