package top.codecafe.app.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import top.codecafe.app.adapter.WidgetListAdapter
import top.codecafe.app.api.API
import top.codecafe.app.ui.base.BasePullFragment
import top.codecafe.app.ui.base.BaseRecyclerAdapter
import top.codecafe.kjframe.http.HttpCallBack

/**
 * 今日话题，(话题列表)
 * @author kymjs (http://www.kymjs.com/) on 8/13/15.
 */
public class TopicList : BasePullFragment() {

    override fun getRecyclerAdapter(): BaseRecyclerAdapter<*> {
        return WidgetListAdapter(recyclerView, null)
    }

    override fun refreshing() {
        requestData()
    }

    override fun completeRefresh() {
    }

    override fun initWidget(parentView: View?) {
        super.initWidget(parentView)
        recyclerView?.setLayoutManager(LinearLayoutManager(outsideAty, LinearLayoutManager.VERTICAL, false))
    }
    
    override fun requestData() {
        API.getTopicList("CodeCafe", object : HttpCallBack() {
            override fun onSuccess(s: String?) {
                recyclerView?.setAdapter(getRecyclerAdapter())
                refreshLayout?.finishRefreshing()
            }
        })
    }
}
