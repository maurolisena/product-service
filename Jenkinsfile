pipeline {
    agent any

    environment {
        PWD = "${env.WORKSPACE}"
    }

    stages {
        stage('Debug Path') {
            steps {
                echo "Mostrando contenido de mongo-init para verificar..."
                sh '''
                    pwd
                    ls -la $PWD/database/mongo-init
                '''
            }
        }

        stage('Limpiar contenedores y volúmenes') {
            steps {
                echo "Deteniendo y eliminando contenedores y volúmenes previos si existen..."
                sh '''
                    docker compose down --volumes --remove-orphans || true

                    # Forzar eliminación del volumen de datos si existe
                    docker volume rm $(docker volume ls -qf "name=mongo_database_data") || true
                '''
            }
        }

        stage('Build y Levantar Servicios') {
            steps {
                echo "Construyendo imágenes y levantando servicios..."
                sh 'docker compose up -d --build'
            }
        }

        stage('Esperar MongoDB') {
            steps {
                echo "Esperando a que MongoDB esté listo..."
                sh '''
                    until docker exec mongo_database mongosh --eval "db.runCommand('ping').ok" | grep 1; do
                        echo "Esperando MongoDB..."
                        sleep 5
                    done
                '''
            }
        }

        stage('Verificar Contenedores') {
            steps {
                sh 'docker ps'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finalizado'
        }
    }
}