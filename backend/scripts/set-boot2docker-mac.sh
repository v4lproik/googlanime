boot2docker start
boot2docker ssh -- 'echo "DOCKER_TLS=no" | sudo tee -a /var/lib/boot2docker/profile'
boot2docker restart
eval "$(boot2docker shellinit)"
launchctl setenv DOCKER_IP $(boot2docker ip 2>/dev/null)
export DOCKER_IP=$(boot2docker ip 2>/dev/null)
docker run -d -p 9200:9200 -p 9300:9300 -e PUBLISH_AS=192.168.59.103:9300 itzg/elasticsearch
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=s3cretP4ssword123456789OverrideInPipeline -e MYSQL_DATABASE=googlanime mysql:5.7
eval "$(boot2docker shellinit)"
