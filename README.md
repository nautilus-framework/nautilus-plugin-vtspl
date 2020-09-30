# Nautilus Plugin VTSPL

Nautilus plugin for the Variability Testing of Software Product Lines (VTSPL) problem

[![GitHub Release](https://img.shields.io/github/release/nautilus-framework/nautilus-plugin-vtspl.svg)](https://github.com/nautilus-framework/nautilus-plugin-vtspl/releases/latest)
[![GitHub contributors](https://img.shields.io/github/contributors/nautilus-framework/nautilus-plugin-vtspl.svg)](https://github.com/nautilus-framework/nautilus-plugin-vtspl/graphs/contributors)
[![GitHub stars](https://img.shields.io/github/stars/nautilus-framework/nautilus-plugin-vtspl.svg)](https://github.com/almende/nautilus-framework/nautilus-plugin-vtspl)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

## Requirements

You need to download Nautilus Framework in your machine (available at https://github.com/nautilus-framework/nautilus-framework) before installing this plugin

## Usage

Open the pom.xml file at ```nautilus-web``` module (from Nautilus Framework) and add the following maven dependency:

```xml
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

## Questions or Suggestions

Feel free to create <a href="https://github.com/nautilus-framework/nautilus-plugin-vtspl/issues">issues</a> here as you need

## Contribute

Contributions to the this project are very welcome! We can't do this alone! Feel free to fork this project, work on it and then make a pull request.

## Authors

* **Thiago Ferreira** - *Initial work*

See also the list of [contributors](https://github.com/nautilus-framework/nautilus-plugin-vtspl/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
