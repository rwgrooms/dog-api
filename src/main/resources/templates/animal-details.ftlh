<html>
<head>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
  <header>
    <nav>
      <a href="/api/dogs">Home</a>
      <a href="/api/dogs/create-form">Create</a>
      <a href="/about.html">About</a>
    </nav>
  </header>

  <div id="dog-content" class="centered">
    <h1 id="dog-name">${dog.name}</h1>
    <img id="dog-image" class="detail-img" src="${dog.imgPath}">
    <p id="dog-desc">${dog.description}</p>
    <a href="/api/dogs/edit/${dog.dogId}" class="button">Edit Details</a>
  </div>
</body>
</html>
