pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn package'
                echo 'Build...'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
