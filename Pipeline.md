# What is Jenkins Pipeline?
Jenkins Pipeline (or simply "Pipeline" with a capital "P") is a suite of plugins which supports implementing and integrating continuous delivery pipelines into Jenkins.

A continuous delivery (CD) pipeline is an automated expression of your process for getting software from version control right through to your users and customers. Every change to your software (committed in source control) goes through a complex process on its way to being released. This process involves building the software in a reliable and repeatable manner, as well as progressing the built software (called a "build") through multiple stages of testing and deployment.

Pipeline provides an extensible set of tools for modeling simple-to-complex delivery pipelines "as code" via the Pipeline domain-specific language (DSL) syntax. 

The definition of a Jenkins Pipeline is written into a text file (called a **Jenkinsfile**) which in turn can be committed to a project’s source control repository. 

This is the foundation of "Pipeline-as-code"; treating the CD pipeline as a part of the application to be versioned and reviewed like any other code.

Creating a **Jenkinsfile** and committing it to source control provides a number of immediate benefits:

1. Automatically creates a Pipeline build process for all branches and pull requests.

2. Code review/iteration on the Pipeline (along with the remaining source code).

3. Audit trail for the Pipeline.

4. Single source of truth [3] for the Pipeline, which can be viewed and edited by multiple members of the project.

While the syntax for defining a Pipeline, either in the web UI or with a **Jenkinsfile** is the same, it is generally considered best practice to define the Pipeline in a Jenkinsfile and check that in to source control.

# Declarative versus Scripted Pipeline syntax
A Jenkinsfile can be written using two types of syntax — Declarative and Scripted.

Declarative and Scripted Pipelines are constructed fundamentally differently. 
Declarative Pipeline is designed to make writing and reading Pipeline code easier, and provides richer syntactical features over Scripted Pipeline syntax.

# Why Pipeline?
Jenkins is, fundamentally, an automation engine which supports a number of automation patterns. Pipeline adds a powerful set of automation tools onto Jenkins, supporting use cases that span from simple continuous integration to comprehensive CD pipelines. By modeling a series of related tasks, users can take advantage of the many features of Pipeline:

**Code**: Pipelines are implemented in code and typically checked into source control, giving teams the ability to edit, review, and iterate upon their delivery pipeline.

**Durable**: Pipelines can survive both planned and unplanned restarts of the Jenkins controller.

**Pausable**: Pipelines can optionally stop and wait for human input or approval before continuing the Pipeline run.

**Versatile**: Pipelines support complex real-world CD requirements, including the ability to fork/join, loop, and perform work in parallel.

**Extensible**: The Pipeline plugin supports custom extensions to its DSL [1] and multiple options for integration with other plugins.

# Pipeline concepts
The following concepts are key aspects of Jenkins Pipeline, which tie in closely to Pipeline syntax (refer to the overview below).

**Pipeline**
A Pipeline is a user-defined model of a CD pipeline. A Pipeline’s code defines your entire build process, which typically includes stages for building an application, testing it and then delivering it.

Also, a pipeline block is a key part of Declarative Pipeline syntax.

**Node**
A node is a machine which is part of the Jenkins environment and is capable of executing a Pipeline.

Also, a node block is a key part of Scripted Pipeline syntax.

**Stage**
A stage block defines a conceptually distinct subset of tasks performed through the entire Pipeline (e.g. "Build", "Test" and "Deploy" stages), which is used by many plugins to visualize or present Jenkins Pipeline status/progress. [6]

**Step**
A single task. Fundamentally, a step tells Jenkins what to do at a particular point in time (or "step" in the process). For example, to execute the shell command make, use the sh step: sh 'make'. When a plugin extends the Pipeline DSL, [1] that typically means the plugin has implemented a new step.

# Pipeline syntax overview
The following Pipeline code skeletons illustrate the fundamental differences between Declarative Pipeline syntax and Scripted Pipeline syntax.

Be aware that both stages and steps (above) are common elements of both Declarative and Scripted Pipeline syntax.

**Declarative Pipeline fundamentals**
In Declarative Pipeline syntax, the pipeline block defines all the work done throughout your entire Pipeline.

<img width="642" height="499" alt="image" src="https://github.com/user-attachments/assets/56325e5a-25c4-4218-a735-1a6207236eaf" />

1. Execute this Pipeline or any of its stages, on any available agent.  
2. Defines the "Build" stage.   
3. Perform some steps related to the "Build" stage.   
4. Defines the "Test" stage.   
5. Perform some steps related to the "Test" stage.
6. Defines the "Deploy" stage.
11. Perform some steps related to the "Deploy" stage.

**Scripted Pipeline fundamentals**
In Scripted Pipeline syntax, one or more node blocks do the core work throughout the entire Pipeline. Although this is not a mandatory requirement of Scripted Pipeline syntax, confining your Pipeline’s work inside of a node block does two things:

1. Schedules the steps contained within the block to run by adding an item to the Jenkins queue. As soon as an executor is free on a node, the steps will run.=
2. Creates a workspace (a directory specific to that particular Pipeline) where work can be done on files checked out from source control.
**Caution**: Depending on your Jenkins configuration, some workspaces may not get automatically cleaned up after a period of inactivity. Refer to the tickets and discussion linked from JENKINS-2111 for more information.
<img width="436" height="288" alt="image" src="https://github.com/user-attachments/assets/bbaa6891-ebff-4b22-8c59-d5fa2b932ea6" />

1. Execute this Pipeline or any of its stages, on any available agent.
2. Defines the "Build" stage. stage blocks are optional in Scripted Pipeline syntax. However, implementing stage blocks in a Scripted Pipeline provides clearer visualization of each stage's subset of tasks/steps in the Jenkins UI.
3. Perform some steps related to the "Build" stage.
4. Defines the "Test" stage.
5. Perform some steps related to the "Test" stage.
6. Defines the "Deploy" stage.
7. Perform some steps related to the "Deploy" stage.

# Pipeline example
Here is an example of a Jenkinsfile using Declarative Pipeline syntax —
<img width="583" height="588" alt="image" src="https://github.com/user-attachments/assets/0fdac7b7-909c-4838-afe6-36481b0fd0cd" />

1. **pipeline** is Declarative Pipeline-specific syntax that defines a "block" containing all content and instructions for executing the entire Pipeline.
2. **agent** is Declarative Pipeline-specific syntax that instructs Jenkins to allocate an executor (on a node) and workspace for the entire Pipeline.
3. **stage** is a syntax block that describes a stage of this Pipeline. Read more about stage blocks in Declarative Pipeline syntax on the Pipeline syntax page. As mentioned above, stage blocks are optional in Scripted Pipeline syntax.
4. **steps** is Declarative Pipeline-specific syntax that describes the steps to be run in this stage.
5. **sh** is a Pipeline step (provided by the Pipeline: Nodes and Processes plugin) that executes the given shell command.
6. **junit** is another Pipeline step (provided by the JUnit plugin) for aggregating test reports.



