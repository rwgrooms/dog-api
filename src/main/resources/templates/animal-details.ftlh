<html>
<head>
  <link rel="stylesheet" href="css/styles.css">
  <script>
    const dogs = {
      baxter: {
        name: "Baxter",
        image: "images/dog1.jpg",
        desc: "Baxter is a musical prodigy with the utmost of rhythm always making you smile."
      },
      luna: {
        name: "Luna",
        image: "images/dog2.jpg",
        desc: "Luna is a fashion queen. She strikes a pose anytime she sees a camera. Obsessed with squeaky toys."
      },
      oliver: {
        name: "Oliver",
        image: "images/dog3.jpg",
        desc: "Oliver is shy but warms up fast. His sweater collection is bigger than yours. Loves hot dogs."
      },
      daisy: {
        name: "Daisy",
        image: "images/dog4.jpg",
        desc: "Daisy loves bow ties and belly rubs. She insists on morning walks and judges you if you're late."
      },
      rocky: {
        name: "Rocky",
        image: "images/dog5.jpg",
        desc: "Rocky is the tough guy with a soft heart. Sunglasses indoors? Always. Hates baths."
      },
      milo: {
        name: "Milo and Otis",
        image: "images/dog6.jpg",
        desc: "Milo and Otis are goofy cuddlebugs. They think they're superheros. Fearless except for vacuum cleaners."
      }
    };

    window.onload = () => {
      const params = new URLSearchParams(location.search);
      const dogKey = params.get("dog");
      const dog = dogs[dogKey];

      if (dog) {
        document.getElementById("dog-name").textContent = dog.name;
        document.getElementById("dog-image").src = dog.image;
        document.getElementById("dog-image").alt = dog.name;
        document.getElementById("dog-desc").textContent = dog.desc;
      } else {
        document.getElementById("dog-content").textContent = "Dog not found.";
      }
    };
  </script>
</head>
<body>
  <header>
    <nav>
      <a href="index.html">Home</a>
      <a href="about.html">About</a>
    </nav>
  </header>

  <div id="dog-content" class="centered">
    <h1 id="dog-name"></h1>
    <img id="dog-image" class="detail-img">
    <p id="dog-desc"></p>
  </div>
</body>
</html>
