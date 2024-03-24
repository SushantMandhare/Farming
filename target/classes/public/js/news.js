async function getAllblogs(){
    const response = await fetch("/blogs");
    const blogs = await response.json();
    if (blogs.length > 0) {
      var temp = "";
      blogs.forEach((itemData) => {
        let imagePath = itemData.imagePath.replace("C:\\Users\\Sushant\\backendcode\\farming\\src\\main\\resources\\public\\", "");
        let videoPath = itemData.videoPath.replace("C:\\Users\\Sushant\\backendcode\\farming\\src\\main\\resources\\public\\", "");

        temp +="    <div class='news_home_1i clearfix'>";
        temp +="    <img src='"+imagePath+"' class='iw' alt='abc'>";
        temp +="    <h3>"+itemData.header+"</h3>";
        temp +="    <p style='text-overflow: ellipsis;word-wrap: nowrap;'>"+itemData.description+"</p>";
        temp +="    <h5><a class='button' href='news_detail.html?story="+itemData.id+"'>Read All Conversation</a></h5>";
        temp +="    </div><br>";
      });
        document.getElementById('blogsdata').innerHTML = temp;
    }else{
        document.getElementById('blogsdata').innerHTML = "";
    }
}