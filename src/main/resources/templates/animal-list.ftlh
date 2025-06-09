<html>
<head>
  <link rel="stylesheet" href="/css/styles.css">
  <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
  <style>
    .form-container {
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }
    .form-field {
      margin-bottom: 15px;
    }
    .form-field label {
      display: block;
      margin-bottom: 5px;
    }
    .form-field input {
      width: 100%;
      padding: 8px;
      box-sizing: border-box;
    }
    .btn {
      border-radius: 4px;
      width: 48%;
    }
    .form-field img {
      max-width: 50px;
      max-height: 50px;
      display: none;
    }
  </style>
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
    <div class="container form-container" style="margin-top: 15px;">
      <form method="get" action="/api/dogs/search" style="margin-bottom: 20px;">
        <input type="text" name="name" placeholder="Search by name..." value="${name!}" />
        <button type="submit">Search</button>
      </form>
    </div>

  <div class="gallery">
   <#list dogsList as dog>
    <div class="card">
      <img src="${dog.imgPath}?t=${.now?long?c}">
      <div>${dog.name}</div>
      <a href="/api/dogs/${dog.dogId}" class="button">View Details</a>
    </div>
   </#list>
  </div>
</body>
</html>
