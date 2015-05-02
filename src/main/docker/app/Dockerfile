FROM dockerfile/java:oracle-java7

WORKDIR /

ADD ${project.artifactId}-${project.version}-${git.buildnumber}.jar .

CMD java -jar /${project.artifactId}-${project.version}-${git.buildnumber}.jar