boot2docker start
boot2docker ssh -- 'echo "DOCKER_TLS=no" | sudo tee -a /var/lib/boot2docker/profile'
boot2docker restart
eval "$(boot2docker shellinit)"
docker run -d -p 9200:9200 -p 9300:9300 -e PUBLISH_AS=192.168.59.103:9300 itzg/elasticsearch
