# Nautilus Plugin VTSPL

Nautilus plugin for the VTSPL problem

## Requirements

You need to download Nautilus Framework in your machine (available at https://github.com/nautilus-framework/nautilus-framework) before installing this plugin

## Usage

Open the pom.xml file at ```nautilus-web``` module (from Nautilus Framework) and add the following maven dependency:

```maven
<dependency>
  <groupId>thiagodnf.nautilus.plugin</groupId>
  <artifactId>nautilus-plugin-vtspl</artifactId>
  <version>1.0.0</version>
</dependency>
```

At ```nautilus-web``` search for ```PluginService```and add the following fragment code at ```loadPluginsFromClasspath``` method:

```java
addProblemExtension(new VTSPLProblemExtension());
```

Run Nautilus Framework as usual

If everything is working well, when you initiate a new execution, the VTSPL problem is going to be available as follows:

![Screen Shot 2020-09-30 at 11 47 07 AM](https://user-images.githubusercontent.com/114015/94708545-bb346580-0312-11eb-99b6-5fba786e071f.png)
