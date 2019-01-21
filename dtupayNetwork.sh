# Create network
docker network create dtupay-net

# Run RabbitMQ container
docker run -d -p5672:5672 --hostname rabbitmq-host --name rabbitmq-container rabbitmq:3

# Connect RabbitMQ container to our network
docker network connect dtupay-net rabbitmq-container