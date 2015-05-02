#Sample docker rest client
This is a sample app inheriting from spring boot to create a REST client container to communicate for the docker-rest-server repository purposes.

It uses the alexecollins [docker-maven-plugin](https://github.com/alexec/docker-maven-plugin "docker-maven-plugin") to create a docker image on package mvn target. 

It assumes a local docker-registry running on port 5000.

When packaging using maven the docker container extracted is TAGed as follows:

> ${project.artifactId}-${project.version}-${git.buildnumber}

where git.buildnumber corresponds to the following git evaluation:

> tag + "_" + branch + "_" + shortRevision + "_" + commitsCount

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

List docker image:

>docker images

Assuming that you run your private docker-registry using the following cmd:

>docker run -p 5000:5000 registry

You can clean, package and deploy docker container to remote registry as follows:

>mvn clean package docker:deploy

NOTE: Seems that you need to prefix "tag" in conf.yml so that it allows connecting to localhost:5000 as follows:

> localhost:5000/your_container_name

#Running the client in docker container
Enter the following command to run the client container:

> docker run -i -t --rm --name client --link docker-server:server net.jufis/docker-rest-client:GIT_TAG

You can find the GIT_TAG if you list the docker images with:

> docker images

Explanation of the last command:

* docker: our linux container
* run   : instructs to run the image 
* --name: the name (ie. client) of our container for linking purposes between the server container. Allows network comms between containers using ssh.
* --link: to which container will talk to. In our case with the server container started with --name docker-server and internally our app will use the alias server to denote the target host (ie. our docker-rest-server) so as to get in contact. Thus our rest client url will contain the url of http://server:8080/greeting.
* docker-rest-client-container: the name of our docker image.

For more on linking containers see the following links:

<a href=https://docs.docker.com/userguide/dockerlinks/>https://docs.docker.com/userguide/dockerlinks/</a>
<br/>
<a href=https://docs.oracle.com/cd/E52668_01/E54669/html/section_rsr_p2z_fp.html>https://docs.oracle.com/cd/E52668_01/E54669/html/section_rsr_p2z_fp.html</a>

#Cleaning up all docker containers

To reset docker from all processes/images run this:

> docker stop $(docker ps -a -q)

>docker rm $(docker ps -a -q)

>docker rmi $(docker images -q)