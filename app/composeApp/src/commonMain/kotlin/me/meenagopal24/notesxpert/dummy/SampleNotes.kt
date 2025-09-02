package me.meenagopal24.notesxpert.dummy


import me.meenagopal24.notexpert.models.Note
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
val notes = listOf(
    // HTML Note 1
    Note(
        title = "Sample HTML Page",
        body = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Sample HTML Page with All Tags</title>
            </head>
            <body>
                <header>
                    <h1>Welcome to My Sample Page</h1>
                    <nav>
                        <ul>
                            <li><a href="#section1">Section 1</a></li>
                            <li><a href="#section2">Section 2</a></li>
                        </ul>
                    </nav>
                </header>
                <main>
                    <section id="section1">
                        <h2>Text Elements</h2>
                        <p>This is a <strong>strong</strong> paragraph with <em>emphasis</em> and <mark>highlight</mark>.</p>
                        <p>This paragraph has <small>small text</small> and <del>deleted text</del>.</p>
                    </section>
                </main>
                <footer>
                    <p>&copy; 2025 Sample HTML Page</p>
                </footer>
            </body>
            </html>
        """.trimIndent(),
        createdAt = Clock.System.now().toEpochMilliseconds()
    ),

    // HTML Note 2
    Note(
        title = "KMP Notes Example",
        body = """
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
        """.trimIndent(),
        createdAt = Clock.System.now().toEpochMilliseconds()
    ),

    // Plain text note 1
    Note(
        title = "Shopping List",
        body = """
            - Milk
            - Eggs
            - Bread
            - Apples
        """.trimIndent(),
        createdAt = Clock.System.now().toEpochMilliseconds()
    ),

    // Plain text note 2
    Note(
        title = "Meeting Notes",
        body = """
            Meeting with the development team at 10 AM.
            - Discuss project roadmap
            - Review UI designs
            - Assign tasks for next sprint
        """.trimIndent(),
        createdAt = Clock.System.now().toEpochMilliseconds()
    ),

    // Plain text note 3
    Note(
        title = "Daily Journal",
        body = """
            Today I went for a long walk in the park and read a few chapters of my book.
            Feeling productive and relaxed.
        """.trimIndent(),
        createdAt = Clock.System.now().toEpochMilliseconds()
    ),

    // Mixed HTML + text
    Note(
        title = "Tips and Tricks",
        body = """
            <h3>HTML Tips</h3>
            <ul>
                <li>Use semantic tags like &lt;header&gt;, &lt;main&gt;, &lt;footer&gt;</li>
                <li>Keep forms accessible with labels</li>
            </ul>
            <p>Remember to write clean and readable code.</p>
        """.trimIndent(),
        createdAt = Clock.System.now().toEpochMilliseconds()
    )
)
