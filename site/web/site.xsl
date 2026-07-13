<xsl:stylesheet version = '1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>
<xsl:output method="html" encoding="UTF-8"/>
<xsl:template match="/">
	<html>
	<head>
	<title>Fusion Language Support - Eclipse Update Site</title>
	<style>@import url("web/site.css");</style>
	</head>
	<body>
	<h1 class="title">Fusion Language Support - Eclipse Update Site</h1>
	<p class="bodyText"><xsl:value-of select="site/description"/></p>
	<p class="bodyText">In Eclipse: Help → Install New Software → Add, then use this page URL as the location.</p>
	<table width="100%" border="0" cellspacing="1" cellpadding="2">
	<tr class="header">
		<td class="sub-header" width="40%">Feature</td>
		<td class="sub-header" width="60%">Version</td>
	</tr>
	<xsl:for-each select="site/feature">
	<tr>
		<xsl:choose>
		<xsl:when test="position() mod 2 = 1">
			<xsl:attribute name="class">dark-row</xsl:attribute>
		</xsl:when>
		<xsl:otherwise>
			<xsl:attribute name="class">light-row</xsl:attribute>
		</xsl:otherwise>
		</xsl:choose>
		<td class="log-text" id="indent"><xsl:value-of select="@id"/></td>
		<td class="log-text" id="indent"><xsl:value-of select="@version"/></td>
	</tr>
	</xsl:for-each>
	</table>
	</body>
	</html>
</xsl:template>
</xsl:stylesheet>
