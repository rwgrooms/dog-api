<html>
<head>
  <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
  <link rel="stylesheet" href="/css/styles.css">
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
  <div class="container form-container" style="margin-top: 15px;">
    <h1>${title}</h1>
    <form id="update-animal" action="/api/dogs/edit/${dog.dogId}" method="post" enctype="multipart/form-data" class="pure-form">
      <div class="form-field">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${dog.name}" placeholder="Name" class="pure-input-1" required/>
      </div>
      <div class="form-field">
        <label for="description">Description</label>
        <input type="text" id="description" name="description" value="${dog.description}" placeholder="Description" class="pure-input-1" required/>
      </div>
      <div class="form-field">
        <label for="breed">Breed</label>
        <input type="text" id="breed" name="breed" value="${dog.breed!}" placeholder="Breed" class="pure-input-1"/>
      </div>
      <div class="form-field">
        <label for="age">Age</label>
        <input type="number" id="age" name="age" value="${dog.age!}" step="0.01" placeholder="Age" class="pure-input-1"/>
      </div>
      <div class="form-field">
        <label for="activeDate">Active Date</label>
        <input type="date" id="activeDate" name="activeDate" value="${(dog.activeDate?string('yyyy-MM-dd'))!''}" placeholder="Active Date" class="pure-input-1"/>
      </div>

        <div class="form-field">
          <label>Current Picture</label><br>
          <img id="image-existing" src="${dog.imgPath}" style="max-height: 150px; display: block;" />
          <input type="hidden" name="existingImagePath" value="${dog.imgPath}" />
        </div>

      <div class="form-field">
        <label for="image">Upload New Picture</label>
        <input type="file" id="image" name="image" accept="image/*" class="pure-input-1" onchange="previewImage(event)" />
        <img id="image-preview" alt="Image Preview" />
      </div>

      <button type="submit" id="save-button" class="pure-button pure-button-primary">Save</button>
      <a href="/api/dogs" class="pure-button pure-button-secondary">Cancel</a>
    </form>
  </div>

  <script>
    function previewImage(event) {
      const imagePreview = document.getElementById('image-preview');
      const file = event.target.files[0];

      if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
          imagePreview.src = e.target.result;
          imagePreview.style.display = 'block';
        };
        reader.readAsDataURL(file);
      } else {
        imagePreview.style.display = 'none';
      }
    }

    const form = document.getElementById('update-animal');
    const saveButton = document.getElementById('save-button');

    form.addEventListener('input', function() {
      const name = form.querySelector('#name').value.trim();
      const description = form.querySelector('#description').value.trim();
      if (name && description) {
        saveButton.disabled = false;
      } else {
        saveButton.disabled = true;
      }
    });

    function initialValidation() {
      const name = form.querySelector('#name').value.trim();
      const description = form.querySelector('#description').value.trim();

      if (!name || !description) {
        saveButton.disabled = true;
      }
    }

    document.addEventListener('DOMContentLoaded', initialValidation);
  </script>
</body>
</html>
