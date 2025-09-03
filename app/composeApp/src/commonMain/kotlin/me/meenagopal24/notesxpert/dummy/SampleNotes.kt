package me.meenagopal24.notesxpert.dummy


import me.meenagopal24.notesxpert.ui.getRandomColor
import me.meenagopal24.notexpert.models.Note
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
val notes = listOf(
    Note(
        title = "Assignment Html Content", body = """
            <h2>Welcome to KMP Notes</h2>
            <p>This is a <b>sample note</b> with HTML and interactive elements.</p>
            <button onclick="showInfo('Clicked on Button 1')">Click Me 1</button>
            <a href="#" onclick="showInfo('Link Clicked')">Click This Link</a>
            <script>
            function showInfo(msg) {
            if (window.JavaScriptBridge) {
            window.JavaScriptBridge.postMessage(msg);
            }
            }
            </script>
        """.trimIndent(), createdAt = Clock.System.now().toEpochMilliseconds(),
        color = getRandomColor().value.toLong()
    ),
    Note(
        title = "Sample Html with Bridge Implementation AI generated Html and CSS", body = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8">
              <title>Bridge Demo</title>
              <style>
                body {
                  font-family: Arial, sans-serif;
                  padding: 20px;
                  background: #f5f5f5;
                  color: #333;
                }

                h2 {
                  color: #444;
                  margin-bottom: 10px;
                }

                .section {
                  margin-bottom: 25px;
                  padding: 15px;
                  background: #fff;
                  border-radius: 8px;
                  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                }

                button {
                  background-color: #4CAF50;
                  border: none;
                  color: white;
                  padding: 10px 16px;
                  text-align: center;
                  text-decoration: none;
                  font-size: 14px;
                  margin-top: 8px;
                  cursor: pointer;
                  border-radius: 6px;
                }

                button:hover {
                  background-color: #45a049;
                }

                a {
                  color: #2196F3;
                  text-decoration: none;
                  font-weight: bold;
                }

                a:hover {
                  text-decoration: underline;
                }

                input[type="text"] {
                  padding: 8px;
                  width: 70%;
                  border: 1px solid #ccc;
                  border-radius: 6px;
                  margin-right: 6px;
                }

                /* Toggle switch */
                .switch {
                  position: relative;
                  display: inline-block;
                  width: 50px;
                  height: 28px;
                }

                .switch input {
                  opacity: 0;
                  width: 0;
                  height: 0;
                }

                .slider {
                  position: absolute;
                  cursor: pointer;
                  top: 0; left: 0; right: 0; bottom: 0;
                  background-color: #ccc;
                  transition: 0.4s;
                  border-radius: 28px;
                }

                .slider:before {
                  position: absolute;
                  content: "";
                  height: 20px; width: 20px;
                  left: 4px;
                  bottom: 4px;
                  background-color: white;
                  transition: 0.4s;
                  border-radius: 50%;
                }

                input:checked + .slider {
                  background-color: #4CAF50;
                }

                input:checked + .slider:before {
                  transform: translateX(22px);
                }
              </style>
            </head>
            <body>

              <h2>Android ↔ JS Bridge Demo</h2>

              <div class="section">
                <h3>1. Button Click</h3>
                <button onclick="sendEvent('Button Clicked!')">Click Me</button>
              </div>

              <div class="section">
                <h3>2. Link Click</h3>
                <a href="#" onclick="sendEvent('Link Clicked!')">Tap this link</a>
              </div>

              <div class="section">
                <h3>3. Toggle Switch</h3>
                <label class="switch">
                  <input type="checkbox" id="toggleSwitch" onclick="toggleChanged()">
                  <span class="slider"></span>
                </label>
              </div>

              <div class="section">
                <h3>4. Custom Input</h3>
                <input type="text" id="customInput" placeholder="Type something...">
                <button onclick="sendInput()">Send</button>
              </div>

              <script>
                function sendEvent(msg) {
                  if (window.JavaScriptBridge) {
                    window.JavaScriptBridge.postMessage(msg);
                  }
                }

                function toggleChanged() {
                  var isOn = document.getElementById("toggleSwitch").checked;
                  sendEvent("Toggle is now " + (isOn ? "ON" : "OFF"));
                }

                function sendInput() {
                  var value = document.getElementById("customInput").value;
                  sendEvent("User input: " + value);
                }
              </script>

            </body>
            </html>

        """.trimIndent(), createdAt = Clock.System.now().toEpochMilliseconds(),
        color = getRandomColor().value.toLong()
    ),
    Note(
        title = "Sample Html With Css", body = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8">
              <meta name="viewport" content="width=device-width, initial-scale=1.0">
              <title>Complete HTML Elements Demo</title>
              <style>
                /* Reset */
                * { box-sizing: border-box; margin: 0; padding: 0; }

                body {
                  font-family: "Segoe UI", Arial, sans-serif;
                  background: #f9fafc;
                  color: #333;
                  line-height: 1.6;
                  padding: 20px;
                }

                header, footer {
                  background: #3f51b5;
                  color: white;
                  padding: 15px 20px;
                  border-radius: 8px;
                  margin-bottom: 20px;
                  position: sticky;
                  top: 0;
                  z-index: 1000;
                }

                header h1, footer p { margin: 0; }

                nav menu {
                  display: flex;
                  gap: 15px;
                  list-style: none;
                  margin-top: 10px;
                }

                nav a {
                  text-decoration: none;
                  color: #ffeb3b;
                  font-weight: bold;
                  transition: color 0.3s;
                }

                nav a:hover { color: white; }

                main {
                  scroll-behavior: smooth;
                }

                main section {
                  background: white;
                  padding: 20px;
                  margin-bottom: 20px;
                  border-radius: 8px;
                  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
                }

                h2 {
                  margin-bottom: 15px;
                  color: #3f51b5;
                  border-bottom: 2px solid #eee;
                  padding-bottom: 5px;
                }

                p, pre, blockquote, address, table, form { margin-bottom: 12px; }

                blockquote {
                  border-left: 4px solid #3f51b5;
                  padding-left: 10px;
                  color: #555;
                  font-style: italic;
                }

                pre {
                  background: #272822;
                  color: #f8f8f2;
                  padding: 10px;
                  border-radius: 5px;
                  overflow-x: auto;
                }

                table {
                  border-collapse: collapse;
                  width: 100%;
                }
                table th, table td {
                  border: 1px solid #ddd;
                  padding: 8px;
                  text-align: center;
                }
                table th {
                  background: #3f51b5;
                  color: white;
                }
                table caption {
                  font-weight: bold;
                  margin-bottom: 5px;
                }

                form input, form select, form textarea, form button {
                  margin: 5px 0;
                  padding: 8px;
                  border: 1px solid #ccc;
                  border-radius: 4px;
                  width: 100%;
                  max-width: 300px;
                }
                form button, form input[type="submit"] {
                  background: #3f51b5;
                  color: white;
                  cursor: pointer;
                  border: none;
                }
                form button:hover, form input[type="submit"]:hover {
                  background: #2c3d9a;
                }

                img, video, audio, canvas, svg, iframe, object, embed {
                  margin: 10px 0;
                  display: block;
                  max-width: 100%;
                  border-radius: 6px;
                }

                details {
                  margin: 10px 0;
                  background: #f1f1f1;
                  padding: 10px;
                  border-radius: 4px;
                }

                progress, meter {
                  display: block;
                  margin: 10px 0;
                  width: 100%;
                  max-width: 300px;
                }

                footer {
                  text-align: center;
                  font-size: 0.9em;
                  margin-top: 40px;
                }
              </style>
              <script>
                function showAlert() { alert("Hello from <script>!"); }
              </script>
            </head>
            <body>

            <header>
              <h1>HTML Elements Showcase</h1>
              <nav>
                <menu>
                  <li><a href="#text">Text</a></li>
                  <li><a href="#lists">Lists</a></li>
                  <li><a href="#media">Media</a></li>
                  <li><a href="#forms">Forms</a></li>
                  <li><a href="#tables">Tables</a></li>
                  <li><a href="#interactive">Interactive</a></li>
                </menu>
              </nav>
            </header>

            <main>
              <!-- Text Elements -->
              <section id="text">
                <h2>Text Elements</h2>
                <p>This is <b>bold</b>, <strong>strong</strong>, <i>italic</i>, <em>emphasis</em>, 
                   <u>underline</u>, <mark>highlight</mark>, <small>small</small>, 
                   <del>deleted</del>, <ins>inserted</ins>, <sub>subscript</sub>, <sup>superscript</sup>.</p>
                <blockquote cite="https://example.com">This is a blockquote.</blockquote>
                <q cite="https://example.com">This is an inline quote.</q>
                <abbr title="HyperText Markup Language">HTML</abbr>
                <address>123 Web Street, Internet City</address>
                <cite>Shakespeare</cite>
                <code>console.log("Hello");</code>
                <kbd>Ctrl + C</kbd>
                <samp>Sample output</samp>
                <var>x</var>
                <pre>Preformatted
              text block</pre>
                <time datetime="2025-09-03">Today</time>
                <br>
                <hr>
              </section>

              <!-- Lists -->
              <section id="lists">
                <h2>Lists</h2>
                <ul>
                  <li>Unordered Item</li>
                  <li>Another Item</li>
                </ul>
                <ol>
                  <li>Ordered Item</li>
                  <li>Another Ordered Item</li>
                </ol>
                <dl>
                  <dt>Term</dt>
                  <dd>Definition</dd>
                </dl>
              </section>

              <!-- Media -->
              <section id="media">
                <h2>Media Elements</h2>
                <img src="https://via.placeholder.com/150" alt="Placeholder">
                <audio controls>
                  <source src="audio.mp3" type="audio/mpeg">
                </audio>
                <video controls width="250">
                  <source src="video.mp4" type="video/mp4">
                </video>
                <figure>
                  <img src="https://via.placeholder.com/100" alt="Figure image">
                  <figcaption>Figure caption</figcaption>
                </figure>
                <canvas id="canvas" width="150" height="100" style="border:1px solid;"></canvas>
                <svg width="100" height="100">
                  <circle cx="50" cy="50" r="40" fill="blue"/>
                </svg>
              </section>

              <!-- Forms -->
              <section id="forms">
                <h2>Form Elements</h2>
                <form>
                  <label for="text">Text:</label>
                  <input type="text" id="text"><br>
                  <input type="password" placeholder="Password"><br>
                  <input type="email" placeholder="Email"><br>
                  <input type="number" min="0" max="10"><br>
                  <input type="range" min="0" max="100"><br>
                  <input type="color"><br>
                  <input type="date"><br>
                  <input type="checkbox" id="check"> <label for="check">Check me</label><br>
                  <input type="radio" name="r"> Option 1
                  <input type="radio" name="r"> Option 2<br>
                  <select>
                    <option>Option A</option>
                    <option>Option B</option>
                  </select><br>
                  <textarea rows="3" cols="20"></textarea><br>
                  <button type="button" onclick="showAlert()">Click Me</button>
                  <input type="submit" value="Submit">
                </form>
              </section>

              <!-- Tables -->
              <section id="tables">
                <h2>Tables</h2>
                <table>
                  <caption>Sample Table</caption>
                  <thead>
                    <tr><th>Header 1</th><th>Header 2</th></tr>
                  </thead>
                  <tbody>
                    <tr><td>Data 1</td><td>Data 2</td></tr>
                  </tbody>
                  <tfoot>
                    <tr><td colspan="2">Footer</td></tr>
                  </tfoot>
                </table>
              </section>

              <!-- Interactive -->
              <section id="interactive">
                <h2>Interactive & Misc</h2>
                <details>
                  <summary>Click to expand</summary>
                  More details here.
                </details>
                <dialog open>This is a dialog element.</dialog>
                <progress value="50" max="100"></progress>
                <meter value="0.6">60%</meter>
                <template>
                  <p>Template content (hidden until cloned).</p>
                </template>
                <iframe src="https://example.com" title="Example"></iframe>
                <object data="sample.pdf" type="application/pdf" width="100" height="100"></object>
                <embed src="sample.mp4" type="video/mp4" width="100" height="100">
              </section>
            </main>

            <footer>
              <p>&copy; 2025 Complete HTML Page</p>
            </footer>

            <noscript>
              <p>JavaScript is disabled.</p>
            </noscript>

            </body>
            </html>

        """.trimIndent(), createdAt = Clock.System.now().toEpochMilliseconds(),
        color = getRandomColor().value.toLong()
    ),
    Note(
        title = "Meeting Notes", body = """
            Meeting with the development team at 10 AM.
            - Discuss project roadmap
            - Review UI designs
            - Assign tasks for next sprint
        """.trimIndent(), createdAt = Clock.System.now().toEpochMilliseconds(),
        color = getRandomColor().value.toLong()
    ),
    Note(
        title = "Daily Journal", body = """
            Today I went for a long walk in the park and read a few chapters of my book.
            Feeling productive and relaxed.
        """.trimIndent(), createdAt = Clock.System.now().toEpochMilliseconds(),
        color = getRandomColor().value.toLong()
    ),
)
