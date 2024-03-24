function GetURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}
async function getNews(){
    var id=GetURLParameter("story");
    const response = await fetch("/blogdetails/"+id);
    const itemData = await response.json();
    if (itemData != "undefined") {
      var temp = "";
        let imagePath = itemData.imagePath.replace("C:\\Users\\Sushant\\backendcode\\farming\\src\\main\\resources\\public\\", "");
        let videoPath = itemData.videoPath.replace("C:\\Users\\Sushant\\backendcode\\farming\\src\\main\\resources\\public\\", "");

        temp +="    <div class='news_home_1i clearfix'>";
        temp +="    <img src='"+imagePath+"' class='iw' alt='abc'>";
        temp +="    <h3>"+itemData.header+"</h3>";
        temp += "   <td><video width='500' height='400' autoplay> <source src='"+videoPath+"'>Your browser does not support the video tag.</video></td>";
        temp +="    <p style='text-overflow: ellipsis;word-wrap: nowrap;'>"+itemData.description+"</p>";
        temp +="    </div><br>";
        document.getElementById('blogsdata').innerHTML = temp;
    }else{
        document.getElementById('blogsdata').innerHTML = "";
    }
}