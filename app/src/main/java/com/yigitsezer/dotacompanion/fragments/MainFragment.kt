package com.yigitsezer.dotacompanion.fragments

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.MobileAds
import com.yigitsezer.dotacompanion.databinding.FragmentMainBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    var binding: FragmentMainBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        /*
        val str = SpannableStringBuilder(binding!!.testText.text)
        str.setSpan(StyleSpan(Typeface.BOLD), 3, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val sourceString = "+ 10 <b>Intelligence</b>"
        binding!!.testText.text = HtmlCompat.fromHtml(sourceString, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding!!.testText.text = str
         */
        /*
        binding!!.frog.itemDescActiveText.text = HtmlCompat.fromHtml("Sweeps a target unit up into a cyclone, making them invulnerable for <b>2.5</b> seconds. Cyclone can only be cast on enemy units or yourself.\n\nEnemy units take <b>50</b> magical damage upon landing.<br><br>Range: <b>575</b><br><br>Dispel Type: <b>Basic Dispel</b>", HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding!!.ice.itemDescPassiveText.text = HtmlCompat.fromHtml("Blinks to and stuns a target enemy unit for <b>2</b> seconds. <br><br>Pierces Spell Immunity.<br><br>Range: <b>550</b><br><br># Passive: Bash<br>Grants melee heroes a <b>25%</b> chance on hit to stun the target for <b>1.5</b> seconds and deal <b>100</b> bonus physical damage.  Bash chance for ranged heroes is <b>10%</b>.<br><br># Passive: Damage Block<br>Grants a <b>50%</b> chance to block <b>70</b> damage from incoming attacks on melee heroes, and <b>35</b> damage on ranged.", HtmlCompat.FROM_HTML_MODE_LEGACY)
         */
        // Inflate the layout for this fragment


        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Sometime in the future fix this
        binding!!.webview.settings.javaScriptEnabled = true
        binding!!.webview.getSettings().setSupportZoom(true)
        binding!!.webview.getSettings().setUseWideViewPort(true)
        binding!!.webview.getSettings().setLoadWithOverviewMode(true)
        binding!!.webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING)
        //binding!!.webview.setWebChromeClient(WebChromeClient())
        //binding!!.webview.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 4.1.1; en-gb; Build/KLP) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30");
        binding!!.webview.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        binding!!.webview.settings.setAppCacheEnabled(true)
        binding!!.webview.settings.setAppCachePath(activity?.cacheDir?.absolutePath)
        binding!!.webview.loadUrl("https://www.dota2.com/patches/7.27d?l=english")
        //binding!!.webview.evaluateJavascript("navBarBGRepeat.style.display = \"none\"", null);
        binding?.webview?.webViewClient = object : WebViewClient() {
            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
                binding?.webview?.evaluateJavascript("navBarBGRepeat.style.display = \"none\";" +
                        "document.getElementsByClassName(\"MainContents\")[0].style.backgroundColor = \"black\";" +
                        "document.getElementsByClassName(\"MainContents\")[0].style.backgroundImage = \"none\";", null)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (!request?.url.toString().contains(Regex("https://"))) {
                    view?.loadUrl(request?.url.toString().replace(Regex("http://"), "https://"))
                    return true
                } else
                    return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding?.progress1?.setVisibility(View.VISIBLE)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding?.progress1?.setVisibility(View.GONE)
            }

            //TODO: Force https or make a button somewhere so user can search through updates
            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                super.doUpdateVisitedHistory(view, url, isReload)
            }
        }
        //binding!!.webview.loadUrl("navBarBGRepeat.style.display = \"none\"")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}