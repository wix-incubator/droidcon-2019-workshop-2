export function init() {
    function stressJSThread() {
        const date = new Date();
        let i = 0;
        while (new Date() - date < 400) i++;
        setTimeout(stressJSThread, 1000);
    }
    stressJSThread();
}
