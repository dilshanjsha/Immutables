package demo.version;

import java.lang.Runtime.Version;

/**
 * 
 * The new pattern of the Version number is:

$FEATURE.$INTERIM.$UPDATE.$PATCH

$FEATURE: counter will be incremented every 

6 months - be ready to catch up the speed !!!!!!

and will be based on feature release versions, e.g: JDK 10, JDK 11.

$INTERIM: counter will be incremented for non-feature releases that contain compatible bug fixes and enhancements but no incompatible changes. Usually this will be zero, as there will be no interim release in a six month period. This kept for future revision to the release model.

$UPDATE: counter will be incremented for compatible update releases that fix security issues, regressions, and bugs in newer features. This is updated one month after the feature release and every 3 months thereafter. The April 2018 release is JDK 10.0.1, the July release is JDK 10.0.2, and so forth

$PATCH: counter will be incremented for an emergency release to fix a critical issue.
New API’s have been added to get these counter values programmatically.

 *
 */
public class JavaTen {

	public JavaTen() {
		
	}

	public static void main(String[] args) {
		Version version = Runtime.version();
		version.feature();
		version.interim();
		version.update();
		version.patch();
		System.out.println(version.feature()+" "+		version.interim()+" "+		version.update()+" "+		version.patch());
	}

}
