# Nautilus Plugin VTSPL

Nautilus plugin for the VTSPL problem

## Requirements

You need to access https://github.com/nautilus-framework/nautilus-framework before installing this plugin

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
