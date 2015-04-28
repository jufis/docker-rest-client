#Sample docker rest client
This is a sample app inheriting from spring boot to create a REST client container to communicate for the docker-rest-server repository purposes.

It uses the spotify [docker-maven-plugin](https://github.com/spotify/docker-maven-plugin "docker-maven-plugin") to create a docker on package mvn target.

#Requirements
You need to have docker install on your linux for this to run (https://docs.docker.com/installation/fedora/)

Quick setup

>$ sudo yum -y remove docker


>$ sudo yum -y install docker-io


>$ sudo yum -y update docker-io

>$ sudo systemctl start docker

>$ sudo systemctl enable docker

>$ sudo groupadd docker

>$ sudo chown root:docker /var/run/docker.sock

>$ sudo usermod -a -G docker $USERNAME

#Build
Setup maven or run from eclipse:

>mvn clean package

#Running the client in docker container
Enter the following command to run the client container:

> docker run --name client --link docker-server:server docker-rest-client-container

Explanation of the command:

* docker: out linux container
* run   : instructs to run the image 
* --name: the name (ie. client) of our container for linking purposes between the server container. Allow network comms between containers using ssh.
* --link: to which container will talk to. In our case with the server container started with --name docker-server and internally our app will use the alias server to denote the target host (ie. our docker-rest-server) so as to get in contact. Thus our rest client url will contain the url of http://server:8080/greeting.
* docker-rest-client-container: the name of our image.

For more on linking containers see the following links:

<a href=https://docs.docker.com/userguide/dockerlinks/>https://docs.docker.com/userguide/dockerlinks/</a>
<br/>
<a href=https://docs.oracle.com/cd/E52668_01/E54669/html/section_rsr_p2z_fp.html>https://docs.oracle.com/cd/E52668_01/E54669/html/section_rsr_p2z_fp.html</a>