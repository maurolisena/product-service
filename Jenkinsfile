pipeline {
    agent any

    stages {
        stage('Preparar workspace y permisos') {
            steps {
                echo "Mostrando ruta y contenido mongo-init"
                sh '''
                    chmod -R 755 ./database/mongo-init
                    ls -la ./database/mongo-init
                '''
            }
        }

        stage('Limpiar contenedores y volúmenes') {
            steps {
                echo "Deteniendo contenedores y eliminando volúmenes y contenedores previos si existen..."
                sh '''
                    docker compose down --volumes --remove-orphans || true
                    docker volume rm mongo_database_data || true
                '''
            }
        }

        stage('Construir y levantar servicios') {
            steps {
                echo "Construyendo imágenes y levantando servicios en background"
                sh 'docker compose up -d --build'
            }
        }

        stage('Esperar a MongoDB listo') {
            steps {
                echo "Esperando que MongoDB esté saludable..."
                sh '''
                    until docker exec mongo_database mongosh --eval "db.runCommand('ping').ok" | grep 1; do
                        echo "Esperando MongoDB..."
                        sleep 5
                    done
                '''
            }
        }

        stage('Verificar contenedores y logs de mongo') {
            steps {
                echo "Listando contenedores activos"
                sh 'docker ps'

                echo "Mostrando últimos logs de mongo_database para verificar scripts init"
                sh 'docker logs --tail 30 mongo_database'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finalizado'
        }
    }
}