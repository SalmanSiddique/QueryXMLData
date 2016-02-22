<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html xmlns="http://www.w3.org/1999/xhtml">
      <body>
	<div style="text-align: center;font-size: 20px">
    <div style="width: 100%; margin: 0 auto;font-size: larger"><b>Programming Assignment 6: Using XPath and XSLT</b></div><div style="margin: 6px"><b>(Developed by: Salman V. Siddique, UTA ID: 1001115361)</b></div>
     <hr/> 
    </div>
	<table border="1">
	  <tr bgcolor="green">
            <th>Id</th>
            <th>Title</th>
            <th width="100px">Date</th>
            <th>Ingredients and Tips</th>
            <th>Preparation</th>
			<th>Comment</th>
			<th>Nutrition</th>
	  </tr>
	  <xsl:for-each select="//recipe">
	    <tr>
	      <td>
			<xsl:value-of select="@id"/>
	      </td>
	      <td><xsl:value-of select="title"/></td>
	      <td>
			<xsl:value-of select="date"/>
		  </td>
	      <td>
		  <table border="1" align="center"><tr>
			<th>Name</th>
            <th>Amount</th>
            <th>Unit</th>
            </tr>
		  <xsl:for-each select="ingredient">
			<tr>
			<td><xsl:value-of select=" @name "/></td>
			<td><xsl:value-of select=" @amount "/></td>
			<td><xsl:value-of select=" @unit "/></td>
			</tr>
			<xsl:for-each select="ingredient">
			<tr>
			<td><xsl:value-of select=" @name "/></td>
			<td><xsl:value-of select=" @amount "/></td>
			<td><xsl:value-of select=" @unit "/></td>
			</tr>
			</xsl:for-each>
			<xsl:for-each select="preparation/step">
			<tr>
			<td colspan="3">
			<xsl:value-of select="."/></td></tr>
			</xsl:for-each>
		  </xsl:for-each>
		  </table>
		  </td>
	      <td>
		  <xsl:for-each select="preparation/step">
			<xsl:value-of select="."/>
		  </xsl:for-each>
		  </td>
		  <td><xsl:value-of select="comment"/></td>
		  <td>
		  <table>
		  <tr><td>Calories: <xsl:value-of select="nutrition/@calories"/></td></tr>
		  <tr><td>Carbohydrates: <xsl:value-of select="nutrition/@carbohydrates"/></td></tr>
		  <tr><td>Fat: <xsl:value-of select="nutrition/@fat"/></td></tr>
		  <tr><td>Protein: <xsl:value-of select="nutrition/@protein"/></td></tr>
		  <tr><td>Alcohol: <xsl:value-of select="nutrition/@alcohol"/></td></tr>
		  </table>
		  </td>
	    </tr>
	  </xsl:for-each>
	</table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>