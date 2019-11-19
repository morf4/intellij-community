# IntelliJ IDEA Community Edition [![official JetBrains project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
These instructions will help you build IntelliJ IDEA Community Edition from source code, which is the basis for IntelliJ Platform development.
The following conventions will be used to refer to directories on your machine:
* `<USER_HOME>` is your home directory.
* `<IDEA_HOME>` is the root directory for the IntelliJ source code.
* `<JDK_16_HOME>` is the root directory for the 1.6 JDK, which is optional.
* `<JDK_18_HOME>` is the root directory for the 1.8 JDK.

## Getting IntelliJ IDEA Community Edition Source Code
IntelliJ IDEA Community Edition source code is available from `github.com/JetBrains/intellij-community` by either cloning or
downloading a zip file (based on a branch) into `<IDEA_HOME>`. The default is the *master* branch. 

The master branch contains the source code which will be used to create the next major version of IntelliJ IDEA. The branch names
and build numbers for older releases of IntelliJ IDEA can be found on the page of
[Build Number Ranges](http://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/build_number_ranges.html).

If you intend to make open source contributions to the IntelliJ Platform,
see [Contributing to the IntelliJ Project](http://www.jetbrains.org/display/IJOS/Contribute) for more information.

_**Speed Tip:**_ If the complete repository history isn't needed then using a shallow clone (`git clone --depth 1`) will save significant time.

These Git operations can also be done through the [IntelliJ IDEA user interface](https://www.jetbrains.com/help/idea/using-git-integration.html).

IntelliJ IDEA Community Edition requires additional Android modules from separate Git repositories. To clone these repositories,
run one of the `getPlugins` scripts located in the `<IDEA_HOME>` directory. These scripts clone their respective *master* branches.
* `getPlugins.sh` for Linux or macOS.
* `getPlugins.bat` for Windows.

_**Note:**_ Always `git checkout` the `intellij-community`, `android`, and `android/tools-base` Git repositories to the same branches/tags. 

## Building IntelliJ Community Edition
Version 2018.2 or newer of IntelliJ IDEA Community Edition or IntelliJ IDEA Ultimate Edition is required to build and develop
for the IntelliJ Platform.

### Opening the IntelliJ Source Code for Build
Using IntelliJ IDEA **File | Open**, select the `<IDEA_HOME>` directory. 
* If IntelliJ IDEA displays an error about a missing or out of date required plugin (e.g. Kotlin),
  [enable, upgrade, or install that plugin](https://www.jetbrains.com/help/idea/managing-plugins.html) and restart IntelliJ IDEA.
* If IntelliJ IDEA displays and error about a Gradle configuration not found,
  [refresh the Gradle projects](https://www.jetbrains.com/help/idea/jetgradle-tool-window.html). 

### IntelliJ Build Configuration
JDK version 1.8 (u91 or newer) is required for building and developing for IntelliJ IDEA Community Edition.
1. Using IntelliJ IDEA, [configure](https://www.jetbrains.com/help/idea/sdk.html) a JDK named "**1.8**", pointing to `<JDK_18_HOME>`.
   * If not already present, add `<JDK_18_HOME>/lib/tools.jar` [to the Classpath](https://www.jetbrains.com/help/idea/sdk.html#manage_sdks) tab
     for the **1.8** JDK.
2. Also configure a JDK named "**IDEA jdk**" (case sensitive), pointing to `<JDK_16_HOME>`. If you don’t want to install JDK 1.6
   then you may configure **IDEA jdk** to point to `<JDK_18_HOME>`. However, you must be careful to avoid using Java 8 APIs
   in IntelliJ IDEA Community Edition modules that use **IDEA jdk**. 
   * If not already present, add the corresponding path for tools.jar to the Classpath for "**IDEA jdk**" JDK.
3. If the _Maven Integration_ plugin is disabled, [add the path variable](https://www.jetbrains.com/help/idea/working-with-projects.html#path-variables)
   "**MAVEN_REPOSITORY**" pointing to `<USER_HOME>/.m2/repository` directory.
4. _**Speed Tip:**_ If you have enough RAM on your computer,
   [configure the compiler settings](https://www.jetbrains.com/help/idea/specifying-compilation-settings.html)
   to enable the "Compile independent modules in parallel" option. Also set the "User-local build process VM options" to `-Xmx2G`.
   These changes will greatly reduce the compile time.

### Building the IntelliJ Application Source Code
To build IntelliJ IDEA Community Edition from source, choose **Build | Build Project** from the main menu.

To build installation packages, run the `ant` command in `<IDEA_HOME>` directory. See the `build.xml` file for details.

## Running IntelliJ IDEA
To run the IntelliJ IDEA built from source, choose **Run | Run** from the main menu. This will use the preconfigured run configuration "**IDEA**".

To run tests on the build, apply these setting to the **Run | Edit Run Configurations... | Defaults | JUnit** configuration tab:
  * Working dir: `<IDEA_HOME>/bin`
  * VM options: 
    * `-ea` 
    * `-Djava.system.class.loader=com.intellij.util.lang.UrlClassLoader` 
    * `-Didea.config.path=../test-config`
    * `-Didea.system.path=../test-system`
 
You can find other helpful information at [http://www.jetbrains.org](http://www.jetbrains.org).
The [contribute section](http://www.jetbrains.org/display/IJOS/Contribute) of that site describes how you can contribute to IntelliJ IDEA.



Apache License
                           Version 2.0, January 2004
                        https://www.apache.org/licenses/

   TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION

   1. Definitions.

      "License" shall mean the terms and conditions for use, reproduction,
      and distribution as defined by Sections 1 through 9 of this document.

      "Licensor" shall mean the copyright owner or entity authorized by
      the copyright owner that is granting the License.

      "Legal Entity" shall mean the union of the acting entity and all
      other entities that control, are controlled by, or are under common
      control with that entity. For the purposes of this definition,
      "control" means (i) the power, direct or indirect, to cause the
      direction or management of such entity, whether by contract or
      otherwise, or (ii) ownership of fifty percent (50%) or more of the
      outstanding shares, or (iii) beneficial ownership of such entity.

      "You" (or "Your") shall mean an individual or Legal Entity
      exercising permissions granted by this License.

      "Source" form shall mean the preferred form for making modifications,
      including but not limited to software source code, documentation
      source, and configuration files.

      "Object" form shall mean any form resulting from mechanical
      transformation or translation of a Source form, including but
      not limited to compiled object code, generated documentation,
      and conversions to other media types.

      "Work" shall mean the work of authorship, whether in Source or
      Object form, made available under the License, as indicated by a
      copyright notice that is included in or attached to the work
      (an example is provided in the Appendix below).

      "Derivative Works" shall mean any work, whether in Source or Object
      form, that is based on (or derived from) the Work and for which the
      editorial revisions, annotations, elaborations, or other modifications
      represent, as a whole, an original work of authorship. For the purposes
      of this License, Derivative Works shall not include works that remain
      separable from, or merely link (or bind by name) to the interfaces of,
      the Work and Derivative Works thereof.

      "Contribution" shall mean any work of authorship, including
      the original version of the Work and any modifications or additions
      to that Work or Derivative Works thereof, that is intentionally
      submitted to Licensor for inclusion in the Work by the copyright owner
      or by an individual or Legal Entity authorized to submit on behalf of
      the copyright owner. For the purposes of this definition, "submitted"
      means any form of electronic, verbal, or written communication sent
      to the Licensor or its representatives, including but not limited to
      communication on electronic mailing lists, source code control systems,
      and issue tracking systems that are managed by, or on behalf of, the
      Licensor for the purpose of discussing and improving the Work, but
      excluding communication that is conspicuously marked or otherwise
      designated in writing by the copyright owner as "Not a Contribution."

      "Contributor" shall mean Licensor and any individual or Legal Entity
      on behalf of whom a Contribution has been received by Licensor and
      subsequently incorporated within the Work.

   2. Grant of Copyright License. Subject to the terms and conditions of
      this License, each Contributor hereby grants to You a perpetual,
      worldwide, non-exclusive, no-charge, royalty-free, irrevocable
      copyright license to reproduce, prepare Derivative Works of,
      publicly display, publicly perform, sublicense, and distribute the
      Work and such Derivative Works in Source or Object form.

   3. Grant of Patent License. Subject to the terms and conditions of
      this License, each Contributor hereby grants to You a perpetual,
      worldwide, non-exclusive, no-charge, royalty-free, irrevocable
      (except as stated in this section) patent license to make, have made,
      use, offer to sell, sell, import, and otherwise transfer the Work,
      where such license applies only to those patent claims licensable
      by such Contributor that are necessarily infringed by their
      Contribution(s) alone or by combination of their Contribution(s)
      with the Work to which such Contribution(s) was submitted. If You
      institute patent litigation against any entity (including a
      cross-claim or counterclaim in a lawsuit) alleging that the Work
      or a Contribution incorporated within the Work constitutes direct
      or contributory patent infringement, then any patent licenses
      granted to You under this License for that Work shall terminate
      as of the date such litigation is filed.

   4. Redistribution. You may reproduce and distribute copies of the
      Work or Derivative Works thereof in any medium, with or without
      modifications, and in Source or Object form, provided that You
      meet the following conditions:

      (a) You must give any other recipients of the Work or
          Derivative Works a copy of this License; and

      (b) You must cause any modified files to carry prominent notices
          stating that You changed the files; and

      (c) You must retain, in the Source form of any Derivative Works
          that You distribute, all copyright, patent, trademark, and
          attribution notices from the Source form of the Work,
          excluding those notices that do not pertain to any part of
          the Derivative Works; and

      (d) If the Work includes a "NOTICE" text file as part of its
          distribution, then any Derivative Works that You distribute must
          include a readable copy of the attribution notices contained
          within such NOTICE file, excluding those notices that do not
          pertain to any part of the Derivative Works, in at least one
          of the following places: within a NOTICE text file distributed
          as part of the Derivative Works; within the Source form or
          documentation, if provided along with the Derivative Works; or,
          within a display generated by the Derivative Works, if and
          wherever such third-party notices normally appear. The contents
          of the NOTICE file are for informational purposes only and
          do not modify the License. You may add Your own attribution
          notices within Derivative Works that You distribute, alongside
          or as an addendum to the NOTICE text from the Work, provided
          that such additional attribution notices cannot be construed
          as modifying the License.

      You may add Your own copyright statement to Your modifications and
      may provide additional or different license terms and conditions
      for use, reproduction, or distribution of Your modifications, or
      for any such Derivative Works as a whole, provided Your use,
      reproduction, and distribution of the Work otherwise complies with
      the conditions stated in this License.

   5. Submission of Contributions. Unless You explicitly state otherwise,
      any Contribution intentionally submitted for inclusion in the Work
      by You to the Licensor shall be under the terms and conditions of
      this License, without any additional terms or conditions.
      Notwithstanding the above, nothing herein shall supersede or modify
      the terms of any separate license agreement you may have executed
      with Licensor regarding such Contributions.

   6. Trademarks. This License does not grant permission to use the trade
      names, trademarks, service marks, or product names of the Licensor,
      except as required for reasonable and customary use in describing the
      origin of the Work and reproducing the content of the NOTICE file.

   7. Disclaimer of Warranty. Unless required by applicable law or
      agreed to in writing, Licensor provides the Work (and each
      Contributor provides its Contributions) on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
      implied, including, without limitation, any warranties or conditions
      of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A
      PARTICULAR PURPOSE. You are solely responsible for determining the
      appropriateness of using or redistributing the Work and assume any
      risks associated with Your exercise of permissions under this License.

   8. Limitation of Liability. In no event and under no legal theory,
      whether in tort (including negligence), contract, or otherwise,
      unless required by applicable law (such as deliberate and grossly
      negligent acts) or agreed to in writing, shall any Contributor be
      liable to You for damages, including any direct, indirect, special,
      incidental, or consequential damages of any character arising as a
      result of this License or out of the use or inability to use the
      Work (including but not limited to damages for loss of goodwill,
      work stoppage, computer failure or malfunction, or any and all
      other commercial damages or losses), even if such Contributor
      has been advised of the possibility of such damages.

   9. Accepting Warranty or Additional Liability. While redistributing
      the Work or Derivative Works thereof, You may choose to offer,
      and charge a fee for, acceptance of support, warranty, indemnity,
      or other liability obligations and/or rights consistent with this
      License. However, in accepting such obligations, You may act only
      on Your own behalf and on Your sole responsibility, not on behalf
      of any other Contributor, and only if You agree to indemnify,
      defend, and hold each Contributor harmless for any liability
      incurred by, or claims asserted against, such Contributor by reason
      of your accepting any such warranty or additional liability.

   END OF TERMS AND CONDITIONS

   APPENDIX: How to apply the Apache License to your work.

      To apply the Apache License to your work, attach the following
      boilerplate notice, with the fields enclosed by brackets "[]"
      replaced with your own identifying information. (Don't include
      the brackets!)  The text should be enclosed in the appropriate
      comment syntax for the file format. We also recommend that a
      file or class name and description of purpose be included on the
      same "printed page" as the copyright notice for easier
      identification within third-party archives.

   Copyright 2019 Rolando Gopez Lacuata.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       https://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
