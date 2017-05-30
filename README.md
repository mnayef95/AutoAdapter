# AutoAdapter #

Make RecyclerView adapter easy with Apdater annotations.

### What is AutoAdapter? ###

* Auto create RecyclerView adapter from model with annotations.
* 1.0.0

### How do I get set up? ###

**Project-level build.gradle** \(\<project>/build.gradle):
```
#!Groovy

   allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**App-level build.gradle** \(\<project>/\<app-module>/build.gradle):

```
#!Groovy

compile 'com.github.mnayef:AAdapter:v1.0.1'
```



### Support components ###

* ImageView
* TextView
* CheckBox
* RadioButton
* VideoView
* Visibility
* Clickable

### How to use? ###

* Create Java Model.
* Add **@Adapter** above of class name