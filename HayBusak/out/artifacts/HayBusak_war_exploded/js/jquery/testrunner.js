function CountdownTimer(el, options) {
    this.timerElement = el;
    this.timerOptions = new Object();
    this.timerOptions.base = options.base ? options.base : new Date().getTime();
    this.timerOptions.timeout = options.timeout ? options.timeout : 60 * 1000;
    this.timerOptions.onTimeout = options.onTimeout ? options.onTimeout : function(){};
    var _this = this;
    update();

    function update() {
        var left = _this.timerOptions.base + _this.timerOptions.timeout - new Date().getTime();
        if (left <= 0) {
            _this.timerOptions.onTimeout(_this);
            left = 0;
        }

        var date = new Date(left);
        var hours = (date.getHours() - new Date(0).getHours());
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();

        function format(n) { return n > 9 ? n : "0" + n; }
        _this.timerElement.text(format(hours) + ":" + format(minutes) + ":" + format(seconds));

        if (left > 0) setTimeout(update, 500);
    }
}

function TestRunner(el, testRunId, options) {
    this.options = new Object();
    this.options.onAuthError = options.onAuthError ? options.onAuthError : "";
    this.options.onComplete = options.onComplete ? options.onComplete : "";
    this.element = jQuery("#" + el);
    jQuery(this.element).bind('copy', function(e) { return false; });

    var _this = this;

    jQuery.ajax({
        type: "GET",
        url: "/testrunner/testrun/" + testRunId,
        dataType: "json",
        cache: false,
        beforeSend: function() {
            _this.element.html("<span style='font-size:1.3em;'>Ð—Ð°Ð³Ñ€ÑƒÐ·ÐºÐ° ...</span>");
        },
        success: function(text) {
            _this.testRun = text;
            createUserInterface();
        },
        error: function(xhr) { xhrFailure(xhr); }
    });

    function xhrFailure(xhr) {
        var status = 0;
        try { status = xhr.status; } catch(e) {};

        switch (status) {
            case 403 : _this.options.onAuthError(); break;
            case 404 : _this.element.html("<span style='color:red;'>Ð¢ÐµÑÑ‚ Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½.</span>"); break;
            case 409 : _this.element.html("<span style='color:red;'>Ð¢ÐµÑÑ‚ Ð½Ðµ Ð½Ð°Ñ‡Ð°Ñ‚ Ð¸Ð»Ð¸ ÑƒÐ¶Ðµ Ð¿Ñ€Ð¾Ð¹Ð´ÐµÐ½.</span>"); _this.options.onComplete(_this); break;
            default :
                if (status >= 400  && status < 600) _this.element.html("<span style='color:red;'>ÐžÑˆÐ¸Ð±ÐºÐ° " + status + ". ÐŸÐ¾Ð¶Ð°Ð»ÑƒÐ¹ÑÑ‚Ð°, ÑÐ¾Ð¾Ð±Ñ‰Ð¸Ñ‚Ðµ Ð¾Ð± ÑÑ‚Ð¾Ð¼ Ñ€Ð°Ð·Ñ€Ð°Ð±Ð¾Ñ‚Ñ‡Ð¸ÐºÐ°Ð¼ ÑÐµÑ€Ð²Ð¸ÑÐ°.</span>");
                else _this.element.html("<span style='color:red;'>Ð’Ð¾Ð·Ð½Ð¸ÐºÐ»Ð° Ð¾ÑˆÐ¸Ð±ÐºÐ°. ÐŸÑ€Ð¾Ð²ÐµÑ€ÑŒÑ‚Ðµ Ð¸Ð½Ñ‚ÐµÑ€Ð½ÐµÑ‚ ÑÐ¾ÐµÐ´Ð¸Ð½ÐµÐ½Ð¸Ðµ Ð¸ Ð¾Ð±Ð½Ð¾Ð²Ð¸Ñ‚Ðµ ÑÑ‚Ñ€Ð°Ð½Ð¸Ñ†Ñƒ.</span>");
        }
    }

    function createUserInterface() {
        var rootPanel = jQuery(document.createElement('div'));
        rootPanel.addClass('testrunner');
        _this.sidebar = jQuery(document.createElement('div'));
        _this.sidebar.addClass('sidebar').attr("id", "test-sidebar");
        rootPanel.append(_this.sidebar);

        _this.questionPanel = jQuery(document.createElement('div'));
        _this.questionPanel.addClass('question').attr("id", "questionPanel");
        rootPanel.append(_this.questionPanel);

        var timerPanel = document.createElement('div');
        timerPanel.className = 'timer';
        _this.sidebar.append(timerPanel);

        if (_this.testRun.timeLimit > 0) {
            //ÐµÑÑ‚ÑŒ Ð¾Ð³Ñ€Ð°Ð½Ð¸Ñ‡ÐµÐ½Ð¸Ðµ Ð¿Ð¾ Ð²Ñ€ÐµÐ¼ÐµÐ½Ð¸
            CountdownTimer(_this.sidebar.find(".timer"), {
                base: _this.testRun.started + new Date().getTime() - _this.testRun.serverTime,
                timeout: _this.testRun.timeLimit,
                onTimeout: function() { closeTestRun(); }
            });
        }

        //generate sidebar
        _this.sidebar.append("<ul class='items'></ul>");
        jQuery(_this.testRun.questions).each(function(i) {
            _this.sidebar.find('.items').append("<li><b><b>" + (i + 1) + "</b></b></li>");
            var lastLi = _this.sidebar.find('.items li:last');
            lastLi.click(function() {
                showQuestion(i);
            });
            if (_this.testRun.questions[i].state.answered) lastLi.addClass('answered');
        });

        //finish button
        _this.sidebar.append("<p class='buttons'><input type='button' value='Ð—Ð°ÐºÐ¾Ð½Ñ‡Ð¸Ñ‚ÑŒ' /></p>");
        _this.sidebar.find("p input[type=button]").click(function() {
            var unanswered = 0;
            for (var i = 0 ; i < _this.testRun.questions.length ; i++)
                if (!_this.testRun.questions[i].state.answered)
                    unanswered++;

            // answered percent
            var percent = (_this.testRun.questions.length - unanswered) * 100 / _this.testRun.questions.length;

            if (percent < 21) {
                if (confirm('Ð’Ñ‹ Ð½Ðµ Ð¾Ñ‚Ð²ÐµÑ‚Ð¸Ð»Ð¸ Ð½Ð° Ð±Ð¾Ð»ÑŒÑˆÐ¸Ð½ÑÑ‚Ð²Ð¾ Ð²Ð¾Ð¿Ñ€Ð¾ÑÐ¾Ð². ÐŸÑ€Ð°Ð²Ð¸Ð»ÑŒÐ½Ñ‹Ðµ Ð¾Ñ‚Ð²ÐµÑ‚Ñ‹ Ð¿Ð¾ÐºÐ°Ð·Ð°Ð½Ñ‹ Ð½Ðµ Ð±ÑƒÐ´ÑƒÑ‚. Ð”ÐµÐ¹ÑÑ‚Ð²Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ Ð·Ð°Ð²ÐµÑ€ÑˆÐ¸Ñ‚ÑŒ Ñ‚ÐµÑÑ‚?'))
                    closeTestRun();
            } else {
                if (unanswered == 0 || confirm('Ð£ Ð²Ð°Ñ ÐµÑÑ‚ÑŒ Ð½ÐµÐ¾Ñ‚Ð²ÐµÑ‡ÐµÐ½Ð½Ñ‹Ðµ Ð²Ð¾Ð¿Ñ€Ð¾ÑÑ‹. Ð’ÑÐµ Ñ€Ð°Ð²Ð½Ð¾ Ð·Ð°Ð²ÐµÑ€ÑˆÐ¸Ñ‚ÑŒ Ñ‚ÐµÑÑ‚?'))
                    closeTestRun();
            }
        });
        if (_this.testRun.questions.length > 0)
            showQuestion(_this.testRun.currentQuestion);
        _this.element.html("");
        _this.element.append(rootPanel);
    }

    function save(question, emptyAnswer, onSuccess) {
        var answers = "";
        for (var i = 0 ; i < question.state.answers.length ; i++) {
            if (answers.length > 0) answers += ",";
            answers += question.state.answers[i];
        }

        jQuery.ajax({
            type:"GET",
            cache:false,
            dataType:"json",
            url: "/testrunner/save",
            data: ({
                testRun: _this.testRun.id,
                question: question.id,
                currentQuestion: _this.questionIndex,
                // send to server reverted value, but in client it will set after response
                answered: (onSuccess ? !question.state.answered : question.state.answered),
                textAnswer: question.state.textAnswer ? question.state.textAnswer : "",
                answers: answers
            }),
            error:function(xhr) { xhrFailure(xhr); },
            beforeSend:function() { if (onSuccess && !question.state.answered && !emptyAnswer) {
                question.state.answers = new Array(); question.state.textAnswer = "";
                // disable answerPanel
                jQuery("#questionPanel input").attr("disabled", "disabled");
                jQuery('#data-loading').css('display', 'inline');
            }
            },
            success:function(obj) {
                question.state = obj.state;
                checkSidebarSelection();
                jQuery('#data-loading').css('display', 'none');
                jQuery("#questionPanel input[type=button]").removeAttr("disabled");
                if (onSuccess) onSuccess();
            }
        });
    };

    function contains(array, el) {
        var found = false;
        for (var j = 0 ; j < array.length ; j++)
            if (el == array[j]) { found = true; break; }
        return found;
    }

    function remove(array, el) {
        var newArray = new Array();
        for (var j = 0 ; j < array.length ; j++)
            if (el != array[j]) newArray.push(array[j]);
        return newArray;
    }

    function isEmptyAnswer(question, answered) {
        return ((question.answerType == 'SINGLE_CHOICE' && question.state.answers.length == 0)
            || (question.answerType == 'TEXT' && (!question.state.textAnswer || question.state.textAnswer.length == 0))) && (answered ? question.state.answered : !question.state.answered);
    }

    function checkSidebarSelection() {
        var items = _this.sidebar.find(".items li");
        for (var j = 0 ; j < _this.testRun.questions.length; j++)
            if (!_this.testRun.questions[j].state.answered) jQuery(items[j]).removeClass('answered');
            else jQuery(items[j]).addClass('answered');
    }

    function prepareNextQuestion(answerPanel, question) {
        var answered = 0;
        for (var i = 0; i < _this.testRun.questions.length; i++)
            if (_this.testRun.questions[i].state.answered) answered++;

        if (answered == _this.testRun.questions.length && confirm('Ð’Ñ‹ Ð¾Ñ‚Ð²ÐµÑ‚Ð¸Ð»Ð¸ Ð½Ð° Ð²ÑÐµ Ð²Ð¾Ð¿Ñ€Ð¾ÑÑ‹. Ð—Ð°Ð²ÐµÑ€ÑˆÐ¸Ñ‚ÑŒ Ð¿Ñ€Ð¾Ñ…Ð¾Ð¶Ð´ÐµÐ½Ð¸Ðµ Ñ‚ÐµÑÑ‚Ð°?'))
            closeTestRun();

        answerPanel.setBlocked(question.state.answered);
        jQuery("#questionPanel p input[type=button]").val(question.state.answered ? "ÐžÑ‚Ð¼ÐµÐ½Ð¸Ñ‚ÑŒ Ð¾Ñ‚Ð²ÐµÑ‚" : "ÐžÑ‚Ð²ÐµÑ‚Ð¸Ñ‚ÑŒ");

        if (question.state.answered) {
            var j = (_this.questionIndex + 1) % _this.testRun.questions.length;
            while (j != _this.questionIndex) {
                var q = _this.testRun.questions[j];
                if (!q.state.answered) { showQuestion(j); break; }
                j = (j + 1) % _this.testRun.questions.length;
            }
        }
    }

    function showQuestion(number) {
        var prevQuestion =  _this.testRun.questions[(_this.questionIndex ? _this.questionIndex : 0)];
        _this.questionIndex = number;
        save(prevQuestion, isEmptyAnswer(prevQuestion, true));

        _this.sidebar.find(".items li").removeClass("current");
        _this.sidebar.find(".items li:eq(" + number + ")").addClass("current");

        var question = _this.testRun.questions[number];

        //IE normalizes white-spaces when using innerHTML so we use "pre(white-space:normal)" as container
        _this.questionPanel.html("<pre class='q-text'>" + question.text + "</pre>");

        // hilite
        _this.questionPanel.find(".q-text pre code[class]").chili();

        //Ð·Ð°Ð¼ÐµÐ½ÑÐµÐ¼ ÑÐ¸Ð¼Ð²Ð¾Ð»Ñ‹ (Ð·Ð°Ñ‰Ð¸Ñ‚Ð° Ð¾Ñ‚ ÐºÐ¾Ð¿Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ñ ÐºÐ¾Ð´Ð° Ð² Ð˜Ð”Ð•)
        var codeBlocks = _this.questionPanel.find("code");
        for (var k = 0 ; k < codeBlocks.length ; k++)
            permute(codeBlocks[k]);

        var answerPanel = jQuery(document.createElement("ul"));
        answerPanel.addClass("answers");

        if (question.answerType != 'TEXT') {
            jQuery(question.answers).each(function(p) {
                var answerEl = jQuery(document.createElement('li'));

                var single = question.answerType == 'SINGLE_CHOICE';

                answerEl.append("<input type='" + (single ? 'radio' : 'checkbox') + "' id='" + this.id + "' name='" + question.id + "' value='" + this.id + "' />");

                if (contains(question.state.answers, this.id)) {
                    answerEl.find('input').attr('checked', 'checked');
                    if (jQuery.browser.msie && parseInt(jQuery.browser.version) == 7) answerEl.find('input').attr('defaultChecked', 'checked');
                }

                answerEl.find('input').click(function() {
                    if (single) {
                        question.state.answers = new Array();
                        if (!contains(question.state.answers, this.id)) question.state.answers.push(this.id);
                    } else {
                        if (answerEl.find('input').attr('checked')) {
                            if (!contains(question.state.answers, this.id)) question.state.answers.push(this.id);
                        } else question.state.answers = remove(question.state.answers, this.id);
                    }
                });

                answerEl.append("<label for='" + this.id + "'>" + this.text + "</label>");
                if (jQuery.browser.msie && parseInt(jQuery.browser.version) == 7)
                    answerEl.find('label').click(function() { answerEl.find('input').click(); });

                answerPanel.append(answerEl);
            });
        } else {
            answerPanel.append("<label for='" + question.id + "'>ÐžÑ‚Ð²ÐµÑ‚: </label>");
            answerPanel.append("<input type='text' id='" + question.id + "' value='" + (question.state.textAnswer ? question.state.textAnswer : "") + "' />");
            answerPanel.find("input[type='text']").blur(function() {
                question.state.textAnswer = jQuery(this).val();
            });
        }

        _this.questionPanel.append(answerPanel);

        codeBlocks = answerPanel.find("code");
        for (var j = 0 ; j < codeBlocks.length ; j++)
            permute(codeBlocks[j]);

        answerPanel.setBlocked = function(blocked) {
            var controls = answerPanel.find("input");
            if (blocked) controls.attr("disabled", "disabled");
            else controls.removeAttr("disabled");
        };

        answerPanel.setBlocked(question.state.answered);
        _this.questionPanel.append("<p class='buttons'><input type='button' value='" + (question.state.answered ? "ÐžÑ‚Ð¼ÐµÐ½Ð¸Ñ‚ÑŒ Ð¾Ñ‚Ð²ÐµÑ‚" : "ÐžÑ‚Ð²ÐµÑ‚Ð¸Ñ‚ÑŒ") + "' />" +
            " &nbsp; <img id='data-loading' align='absmiddle' alt='loading' src='/res/img/loading.gif?0' style='display: none;'/></p>");

        var answerBtn = _this.questionPanel.find("p input[type=button]");
        answerBtn.click(function() {
            var emptyAnswer = false;
            if (isEmptyAnswer(question, false)) {
                emptyAnswer = window.confirm('Ð’Ñ‹ Ð½Ðµ Ð¾Ñ‚Ð²ÐµÑ‚Ð¸Ð»Ð¸ Ð½Ð° Ð²Ð¾Ð¿Ñ€Ð¾Ñ, Ñ…Ð¾Ñ‚Ð¸Ñ‚Ðµ Ð¿Ñ€Ð¾Ð´Ð¾Ð»Ð¶Ð¸Ñ‚ÑŒ?');
                if (!emptyAnswer) return;
            }

            jQuery('#data-loading').css('display', 'inline');

            // in case that server is down, empty answers might handled correctly
            if (emptyAnswer || isEmptyAnswer(question, true)) {
                question.state.answered = !question.state.answered;
                checkSidebarSelection();
                prepareNextQuestion(answerPanel, question);
                save(question, emptyAnswer);
            } else
                save(question, emptyAnswer, function() {
                    prepareNextQuestion(answerPanel, question);
                });

            window.scroll(0,0);
        });
    }

    function closeTestRun() {
        jQuery.ajax({
            type: "GET",
            dataType: "text",
            cache: false,
            url: "/testrunner/close/" + _this.testRun.id,
            success: function() { _this.options.onComplete(_this); },
            error: function(xhr) { xhrFailure(xhr); }
        });
    }

    function convert(str) {
        if (str) {
            var mapping = [];
            mapping['a']= 'Ð°'; mapping['c']= 'Ñ'; mapping['e']= 'Ðµ'; mapping['o']= 'Ð¾'; mapping['p']= 'Ñ€';
            mapping['x']= 'Ñ…'; mapping['y']= 'Ñƒ'; mapping['i']= 'Ñ–'; mapping['A']= 'Ð'; mapping['B']= 'Ð’';
            mapping['C']= 'Ð¡'; mapping['E']= 'Ð•'; mapping['O']= 'Ðž'; mapping['H']= 'Ð'; mapping['P']= 'Ð ';
            mapping['X']= 'Ð¥'; mapping['I']= 'Ð†'; mapping['K']= 'Ðš'; mapping['M']= 'Ðœ'; mapping['T']= 'Ð¢';

            var result = "";
            for (var i = 0 ; i < str.length ; i++)
                if (mapping[str.charAt(i)] && Math.random() > 0.5) result += mapping[str.charAt(i)];
                else result += str.charAt(i);
            return result;
        } else return '';
    }

    function permute(element) {
        jQuery(element).contents().each( function () {
            if (this.nodeType == 1) {
                permute(this);
            } else if (this.nodeType == 3) {
                this.nodeValue = convert(this.nodeValue);
            }
        });
    }
}
