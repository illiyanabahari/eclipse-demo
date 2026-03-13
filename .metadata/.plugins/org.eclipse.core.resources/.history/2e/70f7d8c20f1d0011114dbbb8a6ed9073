pipeline {
    agent any

    environment {
        TOMCAT_HOME = 'C:\\apache-tomcat-10'
        WAR_NAME = 'demo-app.war'
    }

    stages {
        stage('Checkout') {
            steps {
                git(branch: 'main', url: 'https://github.com/illiyanabahari/demo-app.git')
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                // Hot deploy WAR to Tomcat
                bat "copy /Y target\\${env.WAR_NAME} ${env.TOMCAT_HOME}\\webapps\\${env.WAR_NAME}"
            }
        }
    }

    post {
        success {
            echo 'Deployment completed successfully!'
        }
        failure {
            echo 'Something went wrong...'
        }
    }
}