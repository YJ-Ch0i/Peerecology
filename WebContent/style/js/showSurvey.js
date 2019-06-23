           var radio_cnt=2;
           var example_cnt=2;
           var exampleChecked_cnt=2;
           function peer_select(){
               var obj = document.getElementsByName('que_type');
                var que_text = document.getElementById("que_text").value;
                
                var checked_index = -1;
               var checked_value = '';
               for( i=0; i<obj.length; i++ ) {
                  if(obj[i].checked) {
                     checked_index = i;
                     checked_value = obj[i].value;
                  }
               }
               var index = opener.document.getElementById("cnt").value;
               var div = opener.document.createElement('div');
               div.innerHTML =  "문항"+index+". <input type=\"text\" id=\"que_text"+index+"\" name=\"que_text"+index+"\" value=\"\" readonly>타입 : <input type=\"text\" id=\"que_type"+index+"\" name=\"que_type"+index+"\" value=\"\" readonly><input type=\"button\" value=\"삭제\" onclick=\"remove_item1(this)\"><br><br>";
                opener.document.getElementById('field').appendChild(div);
                
               opener.document.getElementById("que_text"+index).value = que_text;
               opener.document.getElementById("que_type"+index).value = checked_value;

                window.close();
              }
         function multiple_choice(){
            var obj = document.getElementsByName('que_type');
             var que_text = document.getElementById("que_text2").value;
             var ten_id = document.getElementById("ten_id2").value;
             var exam_text = new Array();
              var exam_cnt = example_cnt - 1;
              var checked_index = -1;
             var checked_value = '';
             for( i=0; i<obj.length; i++ ) {
                if(obj[i].checked) {
                   checked_index = i;
                   checked_value = obj[i].value;
                }
             }
             var index = opener.document.getElementById("cnt").value;
             var div = opener.document.createElement('div');
             div.setAttribute("id","del"+index)
             div.innerHTML =  "문항"+index+". <input type=\"text\" id=\"que_text"+index+"\" name=\"que_text"+index+"\" readonly>성향 : <input type=\"text\" id=\"ten_id"+index+"\" name=\"ten_id"+index+"\" value=\"\" readonly>타입 : <input type=\"text\" id=\"que_type"+index+"\" name=\"que_type"+index+"\" value=\"\" readonly><input type=\"hidden\" id=\"exam_cnt"+index+"\" name=\"exam_cnt"+index+"\" value=\"\" readonly> <input type=\"button\" value=\"삭제\" onclick=\"remove_item2("+index+")\">";
              opener.document.getElementById('field').appendChild(div);
              
             opener.document.getElementById("que_text"+index).value = que_text;
             opener.document.getElementById("ten_id"+index).value = ten_id;
             opener.document.getElementById("que_type"+index).value = checked_value;
             opener.document.getElementById("exam_cnt"+index).value = exam_cnt;
              for(var i=1; i<=exam_cnt; i++){
                 exam_text[i] = document.getElementById("example1_"+i).value;
              }
               
              for(var i=1; i<=exam_cnt; i++){
                 var div = opener.document.createElement('div');
                 div.setAttribute("id","del"+index)
                 if(i==exam_cnt){ div.innerHTML =  "선택지"+i+". <input type=\"text\" id=\"exam_text"+index+"_"+i+"\" name=\"exam_text"+index+"_"+i+"\" value=\"\" readonly><br><br>";}
                 else{div.innerHTML =  "선택지"+i+". <input type=\"text\" id=\"exam_text"+index+"_"+i+"\" name=\"exam_text"+index+"_"+i+"\" value=\"\" readonly>";}
                   opener.document.getElementById('field').appendChild(div);
                 
                 opener.document.getElementById("exam_text"+index+"_"+i).value = exam_text[i];
              }
              
            
           window.close();
         }
         function radio_choice(){
            var obj = document.getElementsByName('que_type');
             var que_text = document.getElementById("que_text3").value;
             var ten_id = document.getElementById("ten_id3").value;
             
              var checked_index = -1;
             var checked_value = '';
             for( i=0; i<obj.length; i++ ) {
                if(obj[i].checked) {
                   checked_index = i;
                   checked_value = obj[i].value;
                }
             }
             var index = opener.document.getElementById("cnt").value;
             var div = opener.document.createElement('div');
             div.setAttribute("id","del"+index)
             div.innerHTML =  "문항"+index+". <input type=\"text\" id=\"que_text"+index+"\" name=\"que_text"+index+"\" readonly>성향 : <input type=\"text\" id=\"ten_id"+index+"\" name=\"ten_id"+index+"\" value=\"\" readonly>타입 : <input type=\"text\" id=\"que_type"+index+"\" name=\"que_type"+index+"\" value=\"\" readonly><input type=\"hidden\" id=\"exam_cnt"+index+"\" name=\"exam_cnt"+index+"\" value=\"\" readonly><input type=\"button\" value=\"삭제\" onclick=\"remove_item2("+index+")\">";
             opener.document.getElementById('field').appendChild(div);
              
             opener.document.getElementById("que_text"+index).value = que_text;
             opener.document.getElementById("ten_id"+index).value = ten_id;
             opener.document.getElementById("que_type"+index).value = checked_value;
          
             var exam_text = new Array();
              var exam_cnt = radio_cnt - 1;
              exam_text[1] = document.getElementById("radio_example1").value;
              exam_text[2] = document.getElementById("radio_example2").value;
            
                 var div = opener.document.createElement('div');
                 div.setAttribute("id","del"+index)
                 div.style.display = "inline";
                 div.innerHTML =  "<input type=\"text\" id=\"radio_example1_"+index+"\" name=\"radio_example1_"+index+"\" value=\"\" readonly>";
                 opener.document.getElementById('field').appendChild(div);
                 
                   for(var i=1; i<=exam_cnt; i++){
                      var div = opener.document.createElement('div');
                      div.setAttribute("id","del"+index)
                      div.style.display = "inline";
                      div.innerHTML = "<input type=\"radio\" checked=\"\">";
                      opener.document.getElementById('field').appendChild(div);
                   }
                   var div = opener.document.createElement('div');
                   div.setAttribute("id","del"+index)
                   div.style.display = "inline";
                  div.innerHTML =  "<input type=\"text\" id=\"radio_example2_"+index+"\" name=\"radio_example2_"+index+"\" value=\"\" readonly><br><br>";
                  opener.document.getElementById('field').appendChild(div);
                 
                opener.document.getElementById("radio_example1_"+index).value = exam_text[1];
                opener.document.getElementById("radio_example2_"+index).value = exam_text[2];
                opener.document.getElementById("exam_cnt"+index).value = exam_cnt;
           
           window.close();
         }
         
         function multiple_answer_choice(){
             var obj = document.getElementsByName('que_type');
             var ans_obj = document.getElementsByName('example_answer');
              var que_text = document.getElementById("que_text4").value;
              var ten_id = document.getElementById("ten_id4").value;
              var exam_text = new Array();
               var exam_cnt = exampleChecked_cnt - 1;
               var checked_index = -1;
              var checked_value = '';
              for( i=0; i<obj.length; i++ ) {
                 if(obj[i].checked) {
                    checked_index = i;
                    checked_value = obj[i].value;
                 }
              }
              var ans_checked_index = -1;
              var ans_checked_value = '';
              for( i=0; i<ans_obj.length; i++ ) {
                 if(ans_obj[i].checked) {
                    ans_checked_index = i;
                    ans_checked_value = ans_obj[i].value;
                 }
              }
              var index = opener.document.getElementById("cnt").value;
              var div = opener.document.createElement('div');
              div.setAttribute("id","del"+index)
              div.innerHTML =  "문항"+index+". <input type=\"text\" id=\"que_text"+index+"\" name=\"que_text"+index+"\" readonly>성향 : <input type=\"text\" id=\"ten_id"+index+"\" name=\"ten_id"+index+"\" value=\"\" readonly>타입 : <input type=\"text\" id=\"que_type"+index+"\" name=\"que_type"+index+"\" value=\"\" readonly>정답:<input type=\"text\" id=\"example_answer"+index+"\" name=\"example_answer"+index+"\" readonly><input type=\"hidden\" id=\"exam_cnt"+index+"\" name=\"exam_cnt"+index+"\" value=\"\" readonly><input type=\"button\" value=\"삭제\" onclick=\"remove_item2("+index+")\">";
               opener.document.getElementById('field').appendChild(div);
               
              opener.document.getElementById("que_text"+index).value = que_text;
              opener.document.getElementById("ten_id"+index).value = ten_id;
              opener.document.getElementById("que_type"+index).value = checked_value;
              opener.document.getElementById("example_answer"+index).value = ans_checked_value;
              opener.document.getElementById("exam_cnt"+index).value = exampleChecked_cnt;
               for(var i=1; i<=exam_cnt; i++){
                  exam_text[i] = document.getElementById("example2_"+i).value;
               }
                
               for(var i=1; i<=exam_cnt; i++){
                  var div = opener.document.createElement('div');
                  div.setAttribute("id","del"+index)
                  if(i==exam_cnt){ div.innerHTML =  "선택지"+i+". <input type=\"text\" id=\"exam_text"+index+"_"+i+"\" name=\"exam_text"+index+"_"+i+"\" value=\"\" readonly><br><br>";}
                  else{div.innerHTML =  "선택지"+i+". <input type=\"text\" id=\"exam_text"+index+"_"+i+"\" name=\"exam_text"+index+"_"+i+"\" value=\"\" readonly>";}
                    opener.document.getElementById('field').appendChild(div);
                  
                  opener.document.getElementById("exam_text"+index+"_"+i).value = exam_text[i];
               }
               
               
             
            window.close();
          }
         
         function essay_question(){
             var obj = document.getElementsByName('que_type');
              var que_text = document.getElementById("que_text5").value;
              var answer_text = document.getElementById("answer_text").value;
              var ten_id = document.getElementById("ten_id5").value;
              var checked_index = -1;
             var checked_value = '';
             for( i=0; i<obj.length; i++ ) {
                if(obj[i].checked) {
                   checked_index = i;
                   checked_value = obj[i].value;
                }
             }
             var index = opener.document.getElementById("cnt").value;
             var div = opener.document.createElement('div');
             div.innerHTML =  "문항"+index+". <input type=\"text\" id=\"que_text"+index+"\" name=\"que_text"+index+"\" value=\"\" readonly> 성향: <input type=\"text\" id=\"ten_id"+index+"\" name=\"ten_id"+index+"\" value=\"\" readonly> 타입 : <input type=\"text\" id=\"que_type"+index+"\" name=\"que_type"+index+"\" value=\"\" readonly>정답처리단어 :<input type=\"text\" id=\"answer_text"+index+"\" name=\"answer_text"+index+"\" value=\"\" readonly><input type=\"button\" value=\"삭제\" onclick=\"remove_item1(this)\"><br><br>";
              opener.document.getElementById('field').appendChild(div);
             opener.document.getElementById("ten_id"+index).value = ten_id;
             opener.document.getElementById("que_text"+index).value = que_text;
             opener.document.getElementById("answer_text"+index).value = answer_text;
             opener.document.getElementById("que_type"+index).value = checked_value;

              window.close();
            }
         
         function add_radiobox(){
            
             var div = document.createElement('div');
             div.style.display = "inline";
             div.innerHTML = "<input type=\"radio\" checked=\"\">";
              document.getElementById('field_radio').appendChild(div);
              radio_cnt++;
          }
          function add_example(){
        
             var div = document.createElement('div');
             div.innerHTML = example_cnt+"번 선택지 : <input type=\"text\" id=\"example1_"+example_cnt+"\" name=\"example1_"+example_cnt+"\" value=\"\">";
             document.getElementById('field_example').appendChild(div);
            example_cnt++;
          }
          function add_exampleChecked(){
              
              var div = document.createElement('div');
              div.innerHTML = exampleChecked_cnt+"번 선택지 : <input type=\"text\" id=\"example2_"+exampleChecked_cnt+"\" name=\"example2_"+exampleChecked_cnt+"\" value=\"\">  " +
              		"<input type=\"radio\" name=\"example_answer\" id=\"example_answer\" value=\""+exampleChecked_cnt+"\">";
               document.getElementById('field_example2').appendChild(div);
               exampleChecked_cnt++;
           } 