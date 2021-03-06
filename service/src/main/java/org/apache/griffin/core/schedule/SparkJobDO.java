package org.apache.griffin.core.schedule;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiangrchen on 4/26/17.
 */
public class SparkJobDO implements Serializable{

    String file;

    String className;

    List<String> args;

    String name;

    String queue;

    Long numExecutors;

    Long executorCores;

    String driverMemory;

    String executorMemory;

    Conf conf;

    List<String> jars;

    List<String> files;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Long getNumExecutors() {
        return numExecutors;
    }

    public void setNumExecutors(Long numExecutors) {
        this.numExecutors = numExecutors;
    }

    public Long getExecutorCores() {
        return executorCores;
    }

    public void setExecutorCores(Long executorCores) {
        this.executorCores = executorCores;
    }

    public String getDriverMemory() {
        return driverMemory;
    }

    public void setDriverMemory(String driverMemory) {
        this.driverMemory = driverMemory;
    }

    public String getExecutorMemory() {
        return executorMemory;
    }

    public void setExecutorMemory(String executorMemory) {
        this.executorMemory = executorMemory;
    }

    public Conf getConf() {
        return conf;
    }

    public void setConf(Conf conf) {
        this.conf = conf;
    }

    public List<String> getJars() {
        return jars;
    }

    public void setJars(List<String> jars) {
        this.jars = jars;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

}
