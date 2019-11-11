<?xml version="1.0" encoding="UTF-8"?>
<WebElementEntity>
   <description></description>
   <name>div_(carousel)carousel(                        interval 5000 changes the speed                    )</name>
   <tag></tag>
   <elementGuidId>59d89162-5db2-4ac5-b4d1-f91f73b6f0b2</elementGuidId>
   <selectorCollection>
      <entry>
         <key>XPATH</key>
         <value></value>
      </entry>
   </selectorCollection>
   <selectorMethod>XPATH</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>tag</name>
      <type>Main</type>
      <value>div</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>class</name>
      <type>Main</type>
      <value>center</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>text</name>
      <type>Main</type>
      <value>
					
			
									


                
                    
                    
                                            
                    
                    
                                                        
                                    
                                                                            
                                                                                                        
                            
                                                                        
                
                
                    $('.carousel').carousel({
                        interval: 5000 //changes the speed
                    });
                
                


                
                    
                    
                                            
                    
                    
                                                        
                                    
                                                                            
                                                                                                        
                            
                                                                        
                
                
                    $('.carousel').carousel({
                        interval: 5000 //changes the speed
                    });
                
                
												
                				
				
					
							2Hrs 
							1Hrs 
							30Min
					

					
				
				
					
				
				
				
				
    
        
            
                
                    MY ACCOUNT : 4FKU01
                
				                     
                        
                            English
                            Spanish
                        
                    
                                                    
                        
                            American Price
                            Decimal Price
                        
                    
                                
                                    Available: 0
                                    Pending: 0
                                    Balance: 0
                                                    Free play: 156.50
                                                    
                        Non Posted Casino: 0
                    
                                                                    
                        
                            
                            Message
7
                        
                    
                                
                                        
                            
                                                            Rules                            
                        
                                                        
            
            
        
    

    
    
    .border-change-pass{
        border: 1px solid #cccccc;
    }
    



  
    
      
        Change Password / 
      
      
          
              
                  
                      
                          Current Password *
                          
                      
                      
                          New Password*
                          
                      
                  
              
              
          
      
      
        Close
        Save changes
      
    
  




$(document).ready(function () {
    $(&quot;#btnMenuChangePassword&quot;).unbind('click');
    $(&quot;#btnMenuChangePassword&quot;).bind('click',function (e) {
        e.preventDefault();
        $(&quot;#curPasswordEntry, #passwordEntry&quot;).val(&quot;&quot;);
        $(&quot;#divMessagePasswordChange&quot;).val(&quot;&quot;);
    });
    $(&quot;#btnChangePassword&quot;).unbind('click');
    $(&quot;#btnChangePassword&quot;).bind('click',function () {
        var valid = false;
        var passwordEntry = $(&quot;#passwordEntry&quot;).val();
        if ((passwordEntry.length >= 1 &amp;&amp; passwordEntry.length &lt;= 10)) {
            var params = {
                CurPassword: $(&quot;#curPasswordEntry&quot;).val(),
                NewPassword: $(&quot;#passwordEntry&quot;).val()
            };
            $.ajax({
                type: &quot;POST&quot;,
                url: &quot;/Pages/changePassword&quot;,
                dataType: &quot;json&quot;,
                data: params,
                success: function (data) {
                    if (data.Response == &quot;0&quot;) {
                        $(&quot;#curPasswordEntry,#passwordEntry&quot;).val(&quot;&quot;);
                        $(&quot;#passwordEntry&quot;).val(&quot;&quot;);
                        $('#mdChangePassword').modal('hide');
                        $(&quot;#divMessagePasswordChange&quot;).html(&quot;&quot;);
                        modalMessages(data.Message, null,'&lt;button type=&quot;button&quot; class=&quot;btn btn-secondary&quot; data-dismiss=&quot;modal&quot;>Close&lt;/button>');
                    } else {
                        $(&quot;#divMessagePasswordChange&quot;).html(data.Message);
                    }
                },
                error: function (request, status, error) {
                    alert(request.responseText);
                }
            });
        } else {
            console.log('hello')
            $(&quot;#divMessagePasswordChange&quot;).html(&quot;Please review your password, the format entered is not valid&quot;);
        }
    });
});


    
        
            
                REPORTS
                DAILY FIGURE
                TRANSACTIONS
                            
        
    



    $(document).ready(function () {

        $(&quot;#select-lang-mobile option[value='eng']&quot;).prop('selected', true);
        $('#select-lang-mobile').bind('change', function () {
            var lang = $(this).find('option:selected').val();
            $.ajax({
                url: &quot;/Sportbook/setUserLanguage&quot;,
                type: 'POST',
                data: {
                    &quot;lang&quot;: lang
                }, success: function (data) {
                    console.log(data);
                }, complete: function () {
                    if (typeof (Storage) !== &quot;undefined&quot;) {
                        localStorage.setItem(&quot;lenguage&quot;, &quot;1&quot;);
                        identyBtn('.inShowWagersReport', '#wagersReportWrap');
                        identyBtn('.showBalanceReport', '#balanceReportWrap');
                        identyBtn('.showAccountHistoryReport', '#accountHistoryReportWrap');
                        identyBtn('.showRollOverReport', '#RollOverReportWrapper');
                    }
                    changeLangue(lang, '/Sportbook');
                }
            });
        });

        $(&quot;#select-priceType-mobile option[value='&quot; + siteCache['customer']['PriceType'] + &quot;']&quot;).prop('selected', true);
        $('#select-priceType-mobile').bind('change', function () {
            var lineFormat = $(this).find('option:selected').val();
            updateSetting(lineFormat);
            createCookie('CurrentTypeFormat', lineFormat, &quot;&quot;);
            createCookie('LineTypeFormat', (lineFormat == &quot;D&quot; ? &quot;Decimal&quot; : &quot;American&quot;), &quot;&quot;);
        });

        $(&quot;#select_lines_layout option[value='&quot; + _LINES_LAYOUT + &quot;']&quot;).prop('selected', true);
        $(&quot;#select_lines_layout&quot;).selectmenu({
            width: '150px',
            change: function (event, ui) {
                var layout = $(this).find('option:selected').val();
                $.ajax({
                    url: &quot;/Sportbook/setUserLinesLayout&quot;,
                    type: 'POST',
                    data: {
                        &quot;lay&quot;: layout
                    }, success: function (data) {
                        _LINES_LAYOUT = layout;
                        updateBetSlip();
                    }, complete: function () {

                    }
                });
            }
        });

    })


				
					
    
        
        
        
            
                All
                                    Free Plays
                            
            
            
                
                    
                        Document#
                        Date
                        Description
                        Amount
                        Balance
                        Details
                    
                
            
            
                
            
            
        
    
    
    
				
				
					

    
    
    
        
            Pendings
            Graded
                                Open Bets
                    
        
    
				
				
					

    
    
    
        
            
                
                
                    
                        This Week
                        Last Week
                        2 Weeks Ago
                        3 Weeks Ago
                    
                
            

            
                
                    
                        
                    
                    
                
            
            
                        
                
                    
                        
                    MondayTuesdayWednesdayThursdayFridaySaturdaySundayTotal
                    
                        
                    
                
            
            

            

            
        
    

				
                                
					
    
    
        
            
                
                    Program: 
                
                
                    Total Bonus Points: 
                
                
                                        
                        
                            
                                
                            
                        
                    
                    
                        
                            Redeem
                        
                    
                                    
            
            
                
                    
                        
                            $ Points
                            $ Free Play Bonus
                        
                    
                    
                    
                
            
        
    
				
                                
					
    
    
        
            
                
                    
                
                
                    From: 
                    
                        
                        
                    
                
                
                    Hour: 
                    
                
                
                    
                
                
                    To: 
                    
                        
                        
                    
                
                
                    Hour: 
                    
                
                
                    
                
            
            
                
                    
                    Straight
                
                
                    
                    If Bet                    
                
                
                    
                    Teaser
                
                
                    
                    Parlay                    
                
                
                    
                    Live                    
                
                
                    
                    Horses
                
                
                    
                    Contest
                
            
                            
                
                    Submit
                
                
                    Total Rollover: 
                
            
        
        
            
        
    


				
				
				
					
					LOADING...
				

			
			
						
				</value>
   </webElementProperties>
   <webElementProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath</name>
      <type>Main</type>
      <value>id(&quot;myOffCanvas&quot;)/div[@class=&quot;center&quot;]</value>
   </webElementProperties>
   <webElementXpaths>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:idRelative</name>
      <type>Main</type>
      <value>//div[@id='myOffCanvas']/div[2]</value>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:neighbor</name>
      <type>Main</type>
      <value>(.//*[normalize-space(text()) and normalize-space(.)='FIGHTING'])[1]/following::div[13]</value>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:neighbor</name>
      <type>Main</type>
      <value>(.//*[normalize-space(text()) and normalize-space(.)='SOCCER'])[1]/following::div[159]</value>
   </webElementXpaths>
   <webElementXpaths>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>xpath:position</name>
      <type>Main</type>
      <value>//div[3]/div[2]</value>
   </webElementXpaths>
</WebElementEntity>
