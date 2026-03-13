pipeline {
    agent any

    tools {
        maven 'Maven3'   // Your Jenkins Maven name
        jdk 'Java17'     // Your Jenkins JDK name
    }

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