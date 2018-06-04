# Components
Componentes para desenvolvimento

Step 1. Add it in your root build.gradle at the end of repositories:
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.cstevanato:Components:1.0.4'
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.cstevanato.Components:circle:1.0.4'
	        implementation 'com.github.cstevanato.Components:edittext:1.0.4'
	}
