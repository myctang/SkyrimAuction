import React from 'react';
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";
import {bindActionCreators} from "redux";
import * as mySellItems from "../actions/mySellActions";
import $ from "jquery";
import {Card, CardTitle, CardText} from 'react-toolbox/lib/card';

class MySellItems extends React.Component {
    constructor(props) {
        super(props);
        if (!!$.cookie('access_token')) {
            console.log("mySellItems->constructor");
            debugger;
            this.props.mySellItemsActions.getMySellItems($.cookie("access_token"));
        }
    }

    tick = () => {
        console.log("started");
        let sec = parseInt($(".timer .seconds").text());
        console.log(sec);
        if (sec === 0) {
            let min = parseInt($(".timer .minutes").text());
            if (min === 0) {
                this.setState({active:false});
                this.props.mySellItemsActions.getMySellItems($.cookie("access_token"));
                return;
            }
            min--;
            $(".timer .minutes").text(min);
            $(".timer .seconds").text("59");
            return;
        }
        sec--;
        $(".timer .seconds").text(sec);
    };

    componentDidMount() {
        this.interval = setInterval(this.tick, 1000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    render() {
        const items = [];
        console.log("MySellItems->render");
        debugger;
        this.props.mySellItems.forEach((item) => {
            let currentDate = new Date();
            let min = Math.floor((item.sellingEnd - currentDate.getTime()) / 60000);
            let sec = Math.floor(((item.sellingEnd - currentDate.getTime()) / 1000)) - min * 60;
            if ((min < 0 || sec < 0) && !item.finished) {
                min = 0;
                sec = 0;
                this.props.mySellItemsActions.getMySellItems($.cookie("access_token"));
            }
            let time = [];
            let finished = [];
            if (!item.finished) {
                time.push(
                    <div>
                        <br/> Закончится через: <span className="timer"><span className="minutes">{min}</span>:<span
                        className="seconds">{sec}</span></span>
                    </div>
                )
            }else{
                finished.push(
                    <div>
                        <br/> <p className="red">Аукцион завершен</p>
                    </div>
                )
            }
            let style = {width: '300px'}
            let lastBidder;
            if (item.lastBidder !== undefined && item.lastBidder !== null){
                lastBidder = <span>Последняя ставка: {item.lastBidder.name} - {item.price}</span>
            } else {
                lastBidder = <span>Пока никто не сделал ставку</span>
            }
            items.push(
                <div className="buyItemCard">
                    <Card style={style}>
                        <CardTitle
                            title={item.item.name}
                            subtitle={item.item.price}
                        />
                        <CardText>Вес:{item.item.weight}
                            <br/> Атака: {item.item.damage}
                            <br/> Защита: {item.item.defence}
                            <br/> {lastBidder}
                            {time}
                            {finished}
                        </CardText>
                    </Card>
                </div>
            );
        });

        return (
            <div className="buyList">
                {items}
            </div>
        )
    }
}

function mapStateToProps(state) {
    console.log("mySellItems->mapStateToProps");
    debugger;
    return {
        mySellItems: state.getMySellItems.mySellItems
    }
}

function mapDispatchToProps(dispatch) {
    return {
        mySellItemsActions: bindActionCreators(mySellItems, dispatch)
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(MySellItems))