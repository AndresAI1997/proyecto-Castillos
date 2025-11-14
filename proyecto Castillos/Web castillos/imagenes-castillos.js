(function () {
  const IMAGE_FILES = [
    "500_333.jpeg",
    "castillo-de-los-mendoza.jpg",
    "castillo-manzanares-el-real-1636196825.avif",
    "depositphotos_5874784-stock-photo-dark-castle.jpg",
    "descarga (1).jfif",
    "descarga.jfif",
    "fotonoticia_20161130125939-16111886429_690.jpg",
    "images (1).jfif",
    "images (2).jfif",
    "images (3).jfif",
    "images (4).jfif",
    "images (5).jfif",
    "images (6).jfif",
    "images.jfif",
    "panoramic-view-of-the-castle-neuschwanstein-after-sunrise-news-photo-1655305893.avif",
  ];

  const BASE_PATH = "fotos castillos/";

  const pickImage = (id) => {
    if (!IMAGE_FILES.length) return "castillo.png";
    if (!id) return IMAGE_FILES[0];
    let hash = 0;
    for (let i = 0; i < id.length; i += 1) {
      hash = (hash + id.charCodeAt(i) * 31) % 2147483647;
    }
    return IMAGE_FILES[Math.abs(hash) % IMAGE_FILES.length];
  };

  const attachImages = () => {
    if (!Array.isArray(window.CASTLES_DATA)) return;
    window.CASTLES_DATA = window.CASTLES_DATA.map((castle) => ({
      ...castle,
      imagen: `${BASE_PATH}${pickImage(castle.id)}`,
    }));
  };

  attachImages();
  window.__pickCastleImage = pickImage;
})();
