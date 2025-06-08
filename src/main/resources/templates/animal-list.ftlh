<html>
<head>
  <link rel="stylesheet" href="/css/styles.css">
  <header>
        <nav>
            <a href="/api/dogs">Home</a>
            <a href="/api/dogs/create-form">Create</a>
            <a href="/about.html">About</a>
        </nav>
    </header>
</head>
<body>
    

  <h1>Dog Costumes</h1>

  <div class="gallery">
   <#list dogsList as dog>
    <div class="card">
      <img src="${dog.imgPath}">
      <div>${dog.name}</div>
      <a href="/api/dogs/${dog.dogId}" class="button">View Details</a>
    </div>
   </#list>
  </div>
</body>
</html>
