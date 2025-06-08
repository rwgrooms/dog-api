<html>
<head>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
  <header>
    <nav>
      <a href="/api/dogs">Home</a>
      <a href="about.html">About</a>
    </nav>
  </header>

  <div id="dog-content" class="centered">
    <h1 id="dog-name">${dog.name}</h1>
    <img id="dog-image" class="detail-img" src="${dog.imgPath}">
    <p id="dog-desc">${dog.description}</p>
  </div>
</body>
</html>
