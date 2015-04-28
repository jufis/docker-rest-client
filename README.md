#Sample docker rest client
This is a sample app inheriting from spring boot to create a REST client for the docker-rest-server repository
It uses the spotify docker-maven-plugin (https://github.com/spotify/docker-maven-plugin) to create a docker on package target.

#Requirements
You need to have docker install on your linux for this to run (https://docs.docker.com/installation/fedora/)

Quick setup

$ sudo yum -y remove docker
$ sudo yum -y install docker-io
$ sudo yum -y update docker-io
$ sudo systemctl start docker
$ sudo systemctl enable docker
$ sudo groupadd docker
$ sudo chown root:docker /var/run/docker.sock
$ sudo usermod -a -G docker $USERNAME

#Running the client in docker container
docker run docker-rest-client-container