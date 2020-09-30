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

At ```nautilus-web``` search for `PluginService` and add the following fragment code at ```loadPluginsFromClasspath``` method:

```java
addProblemExtension(new VTSPLProblemExtension());
```

Run Nautilus Framework as usual. If everything is working well, when you initiate a new execution, the VTSPL problem is going to be available as follows:

<kbd>
  <img src="https://user-images.githubusercontent.com/114015/94708545-bb346580-0312-11eb-99b6-5fba786e071f.png">
</kbd>
  
## Objective Functions

This plugin supports the following objective functions 

- Number Of Products
- Alive Mutants
- Uncovered Pairs
- Similarity
- Cost
- Unselected Features
- Unimportant Features

Please feel free to pull request a new set of objectives.

## Problem Instances

This plugins makes available the following feature model (problem instances) for your experiments:

1. **James:** SPL for collaborative web systems [1]
2. **CAS (Car Audio System):** a SPL to manage automotive sound systems [6]
3. **WS (Weather Station):** SPL for weather forecast systems [2]
4. **E-Shop:** an E-commerce SPL [5]
5. **Drupal:** a modular open source web content management framework [4]
6. **Smarthome v2.2:** SPL for a smart residential solution [3]

The following table shows information about each feature model:

| Feature Model | # of Products  | Alive Mutants | Valid Pairs | # of Features |
|------|:---:|:---:|:---:|:---:|
|		James       |       68            |    106     |    75     |        14 |   
|		CAS         |       450           |    227     |    183    |        21 |
|		WS          |       504           |    357     |    195    |        22 |
|		E-Shop      |      1152           |     94     |    202    |        22 |
|		Drupal      | ~2.09E9     |    2194    |   1081    |        48 |
|		Smarthome   | ~3.87E9     |    2948    |   1710    |        60 |

## Questions or Suggestions

Feel free to create <a href="https://github.com/nautilus-framework/nautilus-plugin-vtspl/issues">issues</a> here as you need

## Contribute

Contributions to the this project are very welcome! We can't do this alone! Feel free to fork this project, work on it and then make a pull request.

## Authors

* **Thiago Ferreira** - *Initial work*

See also the list of [contributors](https://github.com/nautilus-framework/nautilus-plugin-vtspl/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## References

 - [1] D. Benavides, S. Trujillo, and P. Trinidad. On the modularization of feature models. In Proceedings of the 1st European Workshop on Model Transformation (CMT ’06), page 134, Bilbao, Spain, 2005.

 - [2] D. Beuche and M. Dalgarno. Software product line engineering with feature models. Overload Journal, 78:5–8, 2007.

 - [3] C. Henard, M. Papadakis, G. Perrouin, J. Klein, and Y. Le Traon. Multi-objective test generation for software product lines. In Proceedings of the 17th International Software Product Line Conference (SPLC ’13), pages 62–71, Tokyo, Japan, 2013. ACM.

 - [4] J.A. Parejo, A.B. Sánchez, S. Segura, A. Ruiz-Cortés, R. Lopez-Herrejon, and A. Egyed. Multi-objective test case prioritization in highly configurable systems: A case study. Journal of Systems and Software, 122:287–310, September 2016.

 - [5] S.Segura,R.M.Hierons, D.Benavides,and A.Ruiz-Cortés.Automatedtestdatageneration on the analyses of feature models: A metamorphic testing approach. In Proceedings of the 3nd International Conference on Software Testing, Verification and Validation (ICST ’10), pages 35–44, Paris, France, 2010. IEEE.

 - [6] S. Wang, D. Buchmann, S. Ali, A. Gotlieb, D. Pradhan, and M. Liaaen. Multi-objective test prioritization in software product line testing: An industrial case study. In Proceedings of the 18th International Software Product Line Conference (SPLC ’14), pages 32–41, Florence, Italy, 2014. ACM.
