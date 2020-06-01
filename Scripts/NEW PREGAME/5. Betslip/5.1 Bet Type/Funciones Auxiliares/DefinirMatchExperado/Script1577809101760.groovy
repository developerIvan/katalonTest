import org.openqa.selenium.By
import org.openqa.selenium.WebElement

List <WebElement> teamsOftheMatchWeb = CustomKeywords.'com.utils.AutomationUtils.returnElementsObjects'("Css", "tr[gamenum='"+gameNum+"']", 3)

String awayTeam = "";
String homeTeam = "";
for(WebElement teamDescp:teamsOftheMatchWeb){
   String elementDes = teamDescp.getAttribute("class");
   
   if(elementDes.contains("away")){
	   awayTeam = teamDescp.findElement(By.cssSelector("td.border-bottom.teamInfo div.teamName")).getAttribute("innerText");
   }else if(elementDes.contains("home")){
	   homeTeam = teamDescp.findElement(By.cssSelector("td.border-bottom.teamInfo div.teamName")).getAttribute("innerText");
   }
}

String expectedMatch = homeTeam.concat(" vs ").concat(awayTeam);

awayTeam = null;
homeTeam = null;

teamsOftheMatchWeb = null;

return expectedMatch;