<%@page import="java.util.List"%>
<%@page import="com.osintegrators.example.Address"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Granny's Addressbook</title>

<link type="text/css" rel="stylesheet" href="resources/grannystyle.css">
</head>
<%
	Address address = (Address) request.getAttribute("address");
	String nameValue = address != null ? address.getName() : "";
	String addressValue = address != null ? address.getAddress() : "";
	String phoneValue = address != null ? address.getPhone() : "";
	String emailValue = address != null ? address.getEmail() : "";
	int selectedIndex = request.getAttribute("selectedIndex") != null ? Integer.parseInt((String)request.getAttribute("selectedIndex")) : -1;
	List<Address> addressList = (List<Address>) request.getAttribute("addresses"); 
%>
<body>
	<script type="text/javascript">
		function granny_save() { 
			if (addressList.selectedIndex != -1) {
				document.getElementById("oldNameField").value = addressList.options[addressList.selectedIndex].value;
				document.getElementById("selectedIndexField").value=addressList.selectedIndex;
			}
			document.addressForm.action = 'save'; 
			document.addressForm.submit();
		}
		
		function granny_delete() {
			document.addressForm.action = 'delete';
			document.addressForm.submit();			
		}
		
		function granny_get() {
			var addressList = document.getElementById("addressList");
			document.getElementById("oldNameField").value = addressList.options[addressList.selectedIndex].value;
			document.getElementById("selectedIndexField").value=addressList.selectedIndex;
			document.addressForm.action = 'get';
			document.addressForm.submit();
		}
	</script>
<div id="outer">
<div id="inner">
<h2>
	Granny's Addressbook 
</h2>



<div id="content">
	<form id="addressForm" name="addressForm" method="POST" action="">
		<input id="selectedIndexField" name="selectedIndex" type="hidden" value=""/>
		<input id="oldNameField" name="oldName" type="hidden" value=""/>
		<div id="nameEntry">
			<div id="nameLabel" class="label">Name</div><input id="nameField" name="name" type="text" value="<%=nameValue%>"/>
		</div>
		<div id="addressEntry">
			<div id="addressLabel" class="label">Address</div><input id="addressField" name="address" type="text" value="<%=addressValue%>"/>
		</div>	
		<div id="phoneEntry">
			<div id="phoneLabel" class="label">Phone</div><input id="phoneField" name="phone" type="text" value="<%=phoneValue%>"/>
		</div>	 
		<div id="emailEntry">
			<div id="emailLabel" class="label">Email</div><input id="emailField" name="email" type="text" value="<%=emailValue%>"/>
		</div>
		<div id="saveEntry">
			<input id="saveButton" value="Save" onClick="granny_save()" type="button"/>
		</div>	
		<div id="listEntry"> 
			<div id="list">
				<select size="10" id="addressList" name="addressList"  onChange="granny_get()">
					<option value=""/> 
					<%
					for (int i = 0; i < addressList.size(); i++) {
						Address addressOption = addressList.get(i);
						System.out.println("selectedIndex "+selectedIndex+" selected index == i "+(i == selectedIndex-1)+" "+i);
					%>
					<option name="<%=addressOption.getName()%>" value="<%= addressOption.getName() %>" <%= i == selectedIndex-1 ? "selected" : ""%>><%=addressOption.getName()%></option>
					<% 
					}
					%>
				</select>
			</div>
		</div>
		<div id="deleteEntry">
			<input id="deleteButton" value="Delete" onClick="granny_delete()" type="button"/>
		</div>	
	</form>

</div><!-- end content-->
</div><!-- end inner -->
</div><!-- end outer -->
</body>
</html>