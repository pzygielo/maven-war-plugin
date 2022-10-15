/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
import java.util.jar.JarFile;

def warFile = new JarFile( new File( basedir, 'target/MWAR-415-453.23-SNAPSHOT.war' ), false );
def indexEntry = warFile.getEntry( 'index.html' );

assert indexEntry != null;

def indexLines = warFile.getInputStream( indexEntry ).text.readLines();

// https://maven.apache.org/guides/introduction/introduction-to-the-pom.html#project-model-variables
assert indexLines.grep(~/.*Version: [0-9]+\.[0-9]+-SNAPSHOT.*/ ).size() == 1 : 'Project Model Variable shall be resolved.';

// https://maven.apache.org/guides/introduction/introduction-to-the-pom.html#properties
assert indexLines.grep( ~/.*Manual timestamp: [0-9]{4}-[0-9]{2}-[0-9]{2}.*/ ).size() == 1 : 'Property shall be resolved';

// https://maven.apache.org/guides/introduction/introduction-to-the-pom.html#special-variables
assert indexLines.grep( ~/.*Build timestamp: [0-9]{4}-[0-9]{2}-[0-9]{2}.*/ ).size() == 1 : 'Special Variable shall be resolved';

