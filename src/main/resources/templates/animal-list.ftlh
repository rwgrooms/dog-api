<html>
<head>
  <link rel="stylesheet" href="/css/styles.css">
  <header>
        <nav>
            <a href="index.html">Home</a>
            <a href="about.html">About</a>
        </nav>
    </header>
</head>
<body>
    

  <h1>Dog Costumes</h1>

  <div class="gallery">
   <#list dogsList as dog>
    <div class="card">
      <img src="/images/dog1.jpg">
      <div>${dog.name}</div>
      <a href="/api/dogs/${dog.dogId}" class="button">View Details</a>
    </div>
   </#list>
  </div>
</body>
</html>
